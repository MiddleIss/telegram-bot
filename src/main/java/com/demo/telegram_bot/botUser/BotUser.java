package com.demo.telegram_bot.botUser;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotUser {
    @Id
    @Column(name = "ID")
    long id;
    @Column(name = "firstName")
    String firstName;
    @Column(name = "firstName")
    String lastName;
    @Column(name = "firstName")
    String userName;

}
