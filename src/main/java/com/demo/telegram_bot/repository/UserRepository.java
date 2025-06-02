package com.demo.telegram_bot.repository;

import com.demo.telegram_bot.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<BotUser, Long> {
  Optional<BotUser> findByChatId(Long chatId);
}
