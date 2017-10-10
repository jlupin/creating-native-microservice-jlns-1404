package com.example.application.service.interfaces;

import java.util.Map;

public interface WelcomeService {
    Map<String, String> getWelcomeMessage(final Map<String, String> map);
    void logName(final String name);
}