import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Daria on 01.07.2017.
 */
public class DoraBot extends TelegramLongPollingBot {

    // probably it's not good idea to show token here
    // but I hope that nobody will no use it to damage my pretty bot
    private static final String token = "428910742:AAExeR2y1tZqZ50YR4q-jenyUE6XIjvexIg";

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            Long chatId = update.getMessage().getChatId();

            String messageText = update.getMessage().getText();
            System.out.println("User message: " + messageText);

            messageText = (new StringBuffer(messageText)).reverse().toString();

            System.out.println("Bot  answer : " + messageText + "\n");
            SendMessage answer = new SendMessage()
                    .setChatId(chatId)
                    .setText(messageText);

            try {

                sendMessage(answer);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } // end if

    }

    public String getBotUsername() {
        // return bot name
        return getBotToken();
    }

    public String getBotToken() {
        return token;
    }
}
