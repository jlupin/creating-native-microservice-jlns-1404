package com.example.application.service.impl;

import com.example.application.service.interfaces.WelcomeService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service(value = "welcomeService")
public class WelcomeServiceImpl implements WelcomeService {
    public Map<String, String> getWelcomeMessage(final Map<String, String> map) {
        return Collections.singletonMap("message", "Hello " + map.get("name") + "!");
    }

    public void logName(final String name) {
        // do nothing for this tutorial
    }
}