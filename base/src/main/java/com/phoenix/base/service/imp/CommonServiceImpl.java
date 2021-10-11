package com.phoenix.base.service.imp;

import com.phoenix.base.service.CommonService;
import com.phoenix.core.annotation.ApplicationResource;
import org.springframework.stereotype.Service;

@Service(value = "CommonService")
@ApplicationResource(
        displayResource = "common",
        displayAction = "ping",
        httpMethod = "GET",
        description = "Ping service"
)
public class CommonServiceImpl implements CommonService {

    @Override
    public String ping() {
        return "pong";
    }
}
