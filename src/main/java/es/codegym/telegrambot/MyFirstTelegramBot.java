package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static es.codegym.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "Pathot_bot";
    public static final String TOKEN = "7237626482:AAFOmZ0UWkjvrlDZKrEMfSE6TFv1dUEl35E";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Hackear la nevera", "Botón 1"));
        }

        if (getCallbackQueryButtonKey().equals("Botón 1")) {
            setUserGlory(20);
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Tomar una salchicha! +20 de fama", "Botón 2",
                    "Toma un pescado! +20 de fama", "Botón 2",
                    "Tirar la lata de pepinillos! +20 de fama", "Botón 2"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 2")) {
            setUserGlory(20);
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Hackear al robot aspiradora", "Botón 3"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 3")) {
            setUserGlory(30);
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Enviar al robot aspiradora por comida! +30 de fama", "Botón 4",
                    "Dar un paseo en el robot aspiradora! +30 de fama", "Botón 4",
                    "Huir del robot aspirador! +30 de fama", "Botón 4"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 4")) {
            setUserGlory(30);
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Encender la GoPro y ponértela! +30 de fama", "Botón 5"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 5")) {
            setUserGlory(40);
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Hacer una foto con la GoPro! +30 de fama", "Botón 6",
                    "Hacer un video con la GoPro! +30 de fama", "Botón 6"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 6")) {
            setUserGlory(40);
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Hackear la contraseña!", "Botón 7"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 7")) {
            setUserGlory(50);
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Terminar el juego", "Botón 8"));
        }
        if (getCallbackQueryButtonKey().equals("Botón 8")) {
            setUserGlory(50);
            sendTextMessageAsync(FINAL_TEXT );
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}