import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Daria on 01.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        // initialize Api Context
        ApiContextInitializer.init();
        // instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // register our bot
        try {
            botsApi.registerBot(new DoraBot(args[0]));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
