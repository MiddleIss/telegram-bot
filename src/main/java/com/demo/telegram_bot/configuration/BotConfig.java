package com.demo.telegram_bot.configuration;
import com.demo.telegram_bot.myTelegramBot.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.*;
import org.telegram.telegrambots.meta.generics.*;
import org.telegram.telegrambots.updatesreceivers.*;

@Component
public class BotConfig {
    @Value("${8091278757:AAEevuvLWOGAdlxjI9mG2A31s16tWxHyrKg}")
    private String botToken;
    @Value("${@ICECOOLSNUSBOT}")
    private String botUserName;

    private final MyTelegramBot telegramBot;
    public BotConfig(MyTelegramBot telegramBot) throws TelegramApiException {
        this.telegramBot = telegramBot;
        registerBot();
    }

    private void registerBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramBot);
    }

}
