package com.example.Musicschool.telegrambot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class BotService {

//    @Value("${bot.username}")
//    private String username;
//    @Value("${bot.token}")
//    private String token;
//    @Value("${bot.owner}")
//    private String owner;
//
//
//    private ZoneId zoneId = ZoneId.of("Asia/Tashkent");
//
//    private LocalDateTime now;
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if(update.hasMessage() && owner.equals(update.getMessage().getChatId().toString())){
//            sendMessage(new StringBuilder("Music-school serveri ishlamoqda."));
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return username;
//    }
//
//    @Override
//    public String getBotToken() {
//        return token;
//    }
//
////    @PostConstruct
////    public void init(){
////        sendMessage(new StringBuilder("Music-school serveri ishga tushdi."));
////    }
//
//    @PreDestroy
//    public void destroy(){
//        sendMessage(new StringBuilder("Music-school serveri o'chdi."));
//    }
//    @SneakyThrows
//    private void sendMessage(StringBuilder message){
//        now = LocalDateTime.now(zoneId);
//        message.append("\nVaqt: " + now.format(formatter));
//        SendMessage send = new SendMessage();
//        send.setText("<b>" + message + "</b>");
//        send.setParseMode("HTML");
//        send.setChatId(owner);
//        execute(send);
//    }
}
