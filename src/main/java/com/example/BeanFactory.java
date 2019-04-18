package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Bean(name = "localhost")
    public InetAddress localhost() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }
}