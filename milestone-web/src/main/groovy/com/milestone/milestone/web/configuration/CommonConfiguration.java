package com.milestone.milestone.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.milestone.milestone.web", "com.milestone.criteria"})
public class CommonConfiguration {
}
