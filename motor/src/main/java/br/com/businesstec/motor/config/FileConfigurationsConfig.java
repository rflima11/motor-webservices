package br.com.businesstec.motor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:C:\\config\\motor.properties")
public class FileConfigurationsConfig {
}
