package com.netology;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class TelegramBot extends TelegramLongPollingBot {

    public static final String BOT_TOKEN = "5893916393:AAGQmCIxQu4wgeFKXqZdH8isk-2DO2dkM_g";
    public static final String BOT_USERNAME = "myJavaNasaBot_bot";
    public static final String API_KEY = "1DdGzcwrfTKNfeHMboP8BEglPVTspG0um8kPN4Dn";
    public static long chat_id;

    //URI for connection with private api_key
    public static final String URI =
            "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY;

    public TelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void sendMessage(String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chat_id = update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case "/help":
                    sendMessage("Hello, I'm a NASA bot! I send links to pictures upon request." +
                            "Pictures on the NASA website are updated once a day.");
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getUrl(URI));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    sendMessage("I do not understand you. Please try again");
            }
        }
    }
}
