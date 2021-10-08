package com.phoenix.base.config;

import com.google.common.collect.Multimap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.repository.ExceptionRepository;
import com.phoenix.base.repository.ParameterRepository;
import com.phoenix.base.repository.imp.ExceptionRepositoryImp;
import com.phoenix.base.service.AuthorizationService;
import com.phoenix.common.auth.JwtProvider;
import com.phoenix.common.auth.imp.DefaultJwtProvider;
import com.phoenix.common.text.HashingText;
import com.phoenix.common.util.UUIDFactory;
import com.phoenix.common.util.imp.ConcurrentUUIDFactory;
import com.phoenix.core.model.DefaultException;
import lombok.extern.log4j.Log4j2;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration(value = "ApplicationConfiguration")
@Log4j2
public class ApplicationConfiguration {
    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expired}")
    private String jwtExpired;

    @Value("${application.authorization.model-path}")
    private String authorizationModelPath;

    private final ExceptionRepository exceptionRepository;
    private final AuthorizationService authorizationService;
    private final ParameterRepository parameterRepository;

    public ApplicationConfiguration(
            @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP) ExceptionRepositoryImp exceptionRepositoryImp,
            @Qualifier(BeanIds.AUTHORIZATION_SERVICES) AuthorizationService authorizationService,
            @Qualifier(BeanIds.APPLICATION_PARAMETER_REPOSITORY_IMP) ParameterRepository parameterRepository) {
        this.exceptionRepository = exceptionRepositoryImp;
        this.authorizationService = authorizationService;
        this.parameterRepository = parameterRepository;
    }

    /**
     * @return Enforcer của casbin dùng để xác định quyền của subject đối với object
     */
    @Bean(BeanIds.AUTHORIZATION_ENFORCE)
    public Enforcer createAuthorizationEnforcer() {
        Model model = authorizationService.loadModelFromPath(authorizationModelPath);
        Enforcer enforcer = new Enforcer(model);
        enforcer.setAutoNotifyDispatcher(false);
        enforcer.enableAutoSave(false);
        enforcer.enableLog(false);
        log.info("Creating authorization enforcer");
        return enforcer;
    }

    /**
     * @return : List thông tin các Exception được định nghĩa trong database
     */
    @Bean(value = BeanIds.ALL_EXCEPTION)
    public List<DefaultException> getAllException() {
        log.info("Get all exception from database");
        return exceptionRepository.findAll();
    }

    /**
     * @return : Nạp khóa bí mật của ứng dụng.
     */
    @Bean(value = BeanIds.JWT_SECRET_KEY)
    public String getSecretKey() {
        log.info("create secret key");
        return HashingText.hashingSha256(this.secret);
    }

    /**
     * @param secretKey : khóa bí mật của ứng dụng
     * @return : Đối tượng dùng để generate + validate token (dùng trong quá trình xác thực người dùng)
     */
    @Bean(value = BeanIds.JWT_PROVIDER)
    @DependsOn(BeanIds.JWT_SECRET_KEY)
    public JwtProvider getJwtProvider(@Qualifier(BeanIds.JWT_SECRET_KEY) String secretKey) {
        log.info("create jwt provider");
        return new DefaultJwtProvider(secretKey, Long.parseLong(jwtExpired));
    }

    /**
     * @return : Đối tượng sinh mã UUID duy nhất.
     */
    @Bean(value = BeanIds.UUID_FACTORY)
    public UUIDFactory getUUIDFactory() {
        log.info("create UUID factory");
        return new ConcurrentUUIDFactory();
    }

    @Bean(value = BeanIds.APPLICATION_PARAMETER)
    public Multimap<String, String> getApplicationParameters() {
        return parameterRepository.findAll();
    }
}
