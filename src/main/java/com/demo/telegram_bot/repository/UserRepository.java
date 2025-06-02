package com.demo.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
