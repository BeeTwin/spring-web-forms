package com.example.springwebforms.config;

import com.example.springwebforms.loggers.CachedFileEventLogger;
import com.example.springwebforms.loggers.EventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Bean
    public EventLogger getEventLogger() {
        return new CachedFileEventLogger("rapidograph.log", 5);
    }

}
