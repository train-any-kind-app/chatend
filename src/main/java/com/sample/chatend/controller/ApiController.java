package com.sample.chatend.controller;

import com.sample.chatend.model.Chat;
import com.sample.chatend.model.UserSession;
import com.sample.chatend.repository.ChatRepository;
import com.sample.chatend.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController("api")
public class ApiController {
	@Autowired
	UserSessionRepository userRepository;
	@Autowired
	ChatRepository chatRepository;
	@GetMapping("/user")
	public List<UserSession> usersAll(){
		return new ArrayList<>((Collection) userRepository.findAll());
	}

	@PostMapping("/user/{username}/joined")
	public UserSession connectUser(@PathVariable String username,@RequestBody UserSession userWithUpdate){
		// create a user and/or update  status user true,
		UserSession user = null;
		if(user.getStatus() && userRepository.existsById(username)){
			user = userRepository.save(userWithUpdate);
		}else{
			user = userRepository.save(new UserSession(user.getUsername(), true));
		}
		return user;
	}

	@PutMapping("/user/{username}/leave")
	public UserSession disconnectUser(@PathVariable String username,@RequestBody UserSession userWithUpdate){
		//update  status user false,
		UserSession user = null;
		if(userRepository.existsById(username)){
			if(!user.getStatus()){
				user = userRepository.save(userWithUpdate);
			}else{
				user = userRepository.save(new UserSession(user.getUsername(), false));
			}
		}
		return user;
	}

	@GetMapping("/chats") //avoid using it, prefer send it Throught a suscribe socket
	public List<Chat> chatsAll(){
		return new ArrayList<>((Collection) chatRepository.findAll());
	}
}
