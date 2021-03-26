package com.hal.labs.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomReactiveWebServerFactory extends NettyReactiveWebServerFactory {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public WebServer getWebServer(HttpHandler httpHandler) {
        Map<String, HttpHandler> handlerMap = new HashMap<>();
        handlerMap.put(contextPath, httpHandler);
        return super.getWebServer(new ContextPathCompositeHandler(handlerMap));
    }
}
