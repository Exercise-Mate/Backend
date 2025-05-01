package com.f3.exercise_mate.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // connection을 맺을때 CORS 허용
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
                /*
                스프링 5.3, 스프링부트 2.4 버전 부터 Spring Boot에서 CORS 설정 시, .allowCredentials(true)와 .allowedOrigins("*")는 동시 설정을 못하도록 업데이트 되었다.
                모든 주소를 허용하는 대신 특정 패턴만 허용하는 것으로 적용해야한다고 변동됨.

                따라서 .allowedOrigins("*") 대신 .allowedOriginPatterns("*")를 사용하면 에러는 해결된다.
                */
    }

    // 클라이언트가 메시지를 구독할 endpoint를 정의
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 보낸 메시지를 처리할 핸들러의 prefix
        registry.setApplicationDestinationPrefixes("/app");

        // 클라이언트가 구독할 수 있는 prefix
        // broadcast는 /topic, 1:1은 /queue
        registry.enableSimpleBroker("/topic", "/queue");
    }
}