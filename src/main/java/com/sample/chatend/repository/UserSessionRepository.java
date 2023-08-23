package com.sample.chatend.repository;

import com.sample.chatend.model.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends MongoRepository<UserSession, String> {
	@Query("{username:'?0'}")
	UserSession findUserSessionByUserSession(String username);
}
