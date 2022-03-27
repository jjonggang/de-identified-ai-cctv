//package com.example.aicctvbackend.bank;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.*;
//
//@Configuration
//@EnableWebSocketMessageBroker // broker 기반의 high-level WebSocketMessaging을 구현 가능
//// 어떠한 요청에 대해서 서버가 응답할 지를 결정하는 설정파일을 작성.
//public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic"); // topic이 접두어로 붙어있는 클라이언트들에게 메세지를 전달해주는 역할
//        config.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) { // 최초의 websocket을 생성하는 endpoint를 지정해준다. 여기서는 sockJS의 사용유무를 결정가능
//        registry.addEndpoint("/gs-guide-websocket").withSockJS();
//    }
//}
