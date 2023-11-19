package rs.beograd.telegram;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TelegramSender {

    public static final String TEMPLATE = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    public static final String API_TOKEN = "token";
    public static final String CHAT_ID = "@flat_monitor";

    private static final OkHttpClient client = new OkHttpClient();
    public static void send(String text) {
        try {
            Request request = new Request.Builder()
                    .url(String.format(TEMPLATE, API_TOKEN, CHAT_ID, text))
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
