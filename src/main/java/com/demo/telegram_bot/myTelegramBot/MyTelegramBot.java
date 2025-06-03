package com.demo.telegram_bot.myTelegramBot;

import com.demo.telegram_bot.model.BotUser;
import com.demo.telegram_bot.repository.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.*;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {
    @Autowired
    private UserRepository userRepository;
    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Override
    @Transactional
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            Optional<BotUser> existingUser = userRepository.findByChatId(chatId);
            if (messageText.startsWith("/start")) {
                if (existingUser.isEmpty()) {
                    BotUser user = new BotUser(chatId, update.getMessage().getFrom().getFirstName());
                    userRepository.save(user);
                    sendTextMessage(chatId, "Добро пожаловать! " + update.getMessage().getFrom().getFirstName());
                } else {
                    sendTextMessage(chatId, "Вы уже зарегистрированы!");
                }
            } else {
                sendTextMessage(chatId, "Вы сказали: " + messageText);
            }
        }
    }

    private void sendTextMessage(Long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}