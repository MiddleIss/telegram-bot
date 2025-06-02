package com.demo.telegram_bot.configuration;

import com.demo.telegram_bot.myTelegramBot.*;
import jakarta.annotation.*;
import org.springframework.context.annotation.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.*;
import org.telegram.telegrambots.updatesreceivers.*;

@Configuration
public class BotConfig {

  private final MyTelegramBot telegramBot;

  public BotConfig(MyTelegramBot telegramBot) {
    this.telegramBot = telegramBot;
  }

  @PostConstruct
  public void init() {
    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(telegramBot);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Error initializing Telegram bot", e);
    }
  }

}
