package com.metaphorce.common.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for loading environment variables using Dotenv.
 */
@Configuration
public class DotenvConfig {

    /**
     * Creates a Dotenv bean that loads environment variables.
     *
     * @return a Dotenv instance configured to ignore missing files and load environment variables.
     */
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().ignoreIfMissing().load();
    }
}

