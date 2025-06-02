package com.demo.telegram_bot.myTelegramBot;

import com.demo.telegram_bot.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class MyTelegramBot extends TelegramLongPollingBot {
    private String botToken;
    private String botUsername;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            User user = userRepository.findById(chatId).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(chatId);
                newUser.setFirstName(update.getMessage().getChat().getFirstName());
                newUser.setLastName(update.getMessage().getChat().getLastName());
                newUser.setUserName(update.getMessage().getChat().getUserName());
                return userRepository.save(newUser);
            });

            handleMessage(user, messageText, chatId);
        }
    }

    private void handleMessage(User user, String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (text) {
            case "/start":
                message.setText("время оформить вкид");
                userRepository.save(user);
                break;
            case "/help":
                message.setText("Это помощь по боту...");
                break;
            default:
                message.setText("Я не понимаю эту команду.");
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}