package com.sample.chatend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.chatend.config.ILogger;
import com.sample.chatend.config.LoggerImpl;
import com.sample.chatend.model.Chat;
import com.sample.chatend.repository.ChatRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ChatController {
	private SimpMessagingTemplate messagingTemplate;

	//@Qualifier("loggerImpl")
	//@Autowired
	private Logger logger = LoggerImpl.getLogger(this);
	@Autowired
	private ChatRepository chatRepository;

	public ChatController() {}

	@MessageMapping("/public")
	@SendTo("/room/public")
	public Chat publicMessage(@Payload String json){
		logger.info("Chat received: " + json);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Chat chat = objectMapper.readValue(json,Chat.class);
			logger.info("Chat json convert: " + chat.getDate());
			Chat saved = chatRepository.save(chat);
			logger.info("Chat saved: " + saved.getDate());
			//²messagingTemplate.convertAndSend("/room/public", chat);
			return chat;
		}catch(Exception e){
			logger.error("Chat parsed from json Failed. Err => " + e.getMessage());
			return  null;
		}
	}

	@MessageMapping("/private")
	public String privateDirectMessage(@Payload String json) {
		logger.info("Chat received: " + json);
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			Chat chat = objectMapper.readValue(json,Chat.class);
			messagingTemplate.convertAndSendToUser(chat.getRecipent(),"/private", chat);
			chatRepository.save(chat);
		}catch(Exception e){
			logger.error("Chat parsed from json Failed. Err => " + e.getMessage());
		}finally {
			return json;
		}

	}
}
