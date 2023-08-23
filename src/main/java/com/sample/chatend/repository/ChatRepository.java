package com.sample.chatend.repository;

import com.sample.chatend.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
	@Query("{session:'?0'}")
	List<Chat> findAll(String session);

	@Query(value="sender:'?0', recipient:'?0'", fields="{session:'private'}")
	List<Chat> findAll(String sender, String recipient);
}
