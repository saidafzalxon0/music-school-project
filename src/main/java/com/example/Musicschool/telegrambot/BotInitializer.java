package com.example.Musicschool.telegrambot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {
//
//    @Autowired
//    private BotService service;
//
//    @EventListener({ContextRefreshedEvent.class})
//    @SneakyThrows
//    public void init(){
//        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
//        api.registerBot(service);
//    }
}
