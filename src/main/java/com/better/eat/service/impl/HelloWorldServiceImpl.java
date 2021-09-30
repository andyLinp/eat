package com.better.eat.service.impl;

import com.better.eat.service.HelloWorldService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String getWorld(String world) {
        Objects.requireNonNull(world,"world can not be null");
        return world;
    }
}
