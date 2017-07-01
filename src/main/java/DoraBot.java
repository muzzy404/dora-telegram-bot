import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Daria on 01.07.2017.
 */
public class DoraBot extends TelegramLongPollingBot {

    public DoraBot(String token) {
        super();
        this.token = token;
    }

    private final String token;

    private boolean REVERSE_MODE = true;

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            Long chatId = update.getMessage().getChatId();

            String messageText = update.getMessage().getText();
            System.out.println("User message: " + messageText);

            messageText = generateAnswer(messageText);

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

    private String generateAnswer(String userMsg) {

        switch (userMsg) {
            case Commands.HELP:
                userMsg = "no commands yet";
                break;

            case Commands.WHAT_TO_DO:
                userMsg = YouTubeLinks.WHAT_TO_DO;
                break;

            case Commands.WHAT_TO_BUY:
                userMsg = YouTubeLinks.WHAT_TO_BUY;
                break;

            default:
                userMsg = (new StringBuffer(userMsg)).reverse().toString();
        }

        return userMsg;
    }

    private class Commands {
        // commands
        private static final String HELP = "/help";
        // Easter Eggs
        private static final String WHAT_TO_DO = "ля шо бы поделат";
        private static final String WHAT_TO_BUY = "ля шо бы купит";
    }

    private class YouTubeLinks {
        private static final String WHAT_TO_DO = "https://youtu.be/pkQ5yj_bITE";
        private static final String WHAT_TO_BUY = "https://youtu.be/E8IFiUT5zjM";
    }
}
