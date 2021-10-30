package com.phoenix.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

public interface BaseController {
    ResponseEntity<String> ping();
}
