package com.demo.telegram_bot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Builder
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long chatId;

    private String name;

    public BotUser() {
    }

    public BotUser(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }

}
