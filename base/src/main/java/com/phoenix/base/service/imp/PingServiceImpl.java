package com.phoenix.base.service.imp;

import com.phoenix.base.service.PingService;
import com.phoenix.core.annotation.ApplicationResource;
import org.springframework.stereotype.Service;

@Service(value = "PingService")
@ApplicationResource
public class PingServiceImpl implements PingService {

    @Override
    public String ping() {
        return "pong";
    }
}
