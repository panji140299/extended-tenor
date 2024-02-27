package com.panpraz.eventlunas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping
public class HealthCheckController {
    Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

}

