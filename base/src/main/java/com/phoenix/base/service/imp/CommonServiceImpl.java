package com.phoenix.base.service.imp;

import com.phoenix.base.service.CommonService;
import com.phoenix.core.annotation.ApplicationResource;
import com.phoenix.core.annotation.ApplicationResourceAction;
import org.springframework.stereotype.Service;

@Service(value = "CommonService")
@ApplicationResource(
        displayResource = "common",
        httpMethod = "GET",
        description = "Ping service"
)
public class CommonServiceImpl implements CommonService {

    @ApplicationResourceAction(
            displayPath = "ping",
            httpMethod = "PUT",
            description = "abc xyz",
            isEnabled = false
    )
    @Override
    public String ping() {
        return "pong";
    }
}
