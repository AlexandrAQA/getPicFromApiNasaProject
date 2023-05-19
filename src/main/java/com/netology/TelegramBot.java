package com.netology;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot extends TelegramLongPollingBot {

    public static final String BOT_TOKEN = "5893916393:AAGQmCIxQu4wgeFKXqZdH8isk-2DO2dkM_g";
    public static final String BOT_USERNAME = "myJavaNasaBot_bot";
    public static final String API_KEY = "1DdGzcwrfTKNfeHMboP8BEglPVTspG0um8kPN4Dn";

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

    @Override
    public void onUpdateReceived(Update update) {

    }
}
