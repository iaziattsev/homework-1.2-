package WebRequests;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class MovieQuoteAPIImpl implements MovieQuoteAPI {

    private static final String MOVIE_QUOTE_API_URI = "https://movie-quote-api.herokuapp.com/v1/quote/";

    @Override
    public MovieQuoteModel getMovieQuote() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(MOVIE_QUOTE_API_URI).openConnection();

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode != 200) {
                System.out.printf("Error, response code is %d", responseCode);

                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            String response = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            httpURLConnection.disconnect();

            if (response.isEmpty())
                return null;

            return parseQuote(response);
        }
        catch (MalformedURLException ignored) {}
        catch (IOException e) {
            System.out.println("Error connecting to the server");
            e.printStackTrace();
        }

        return null;
    }

    private MovieQuoteModel parseQuote(String json) {
        return new Gson().fromJson(json, MovieQuoteModel.class);
    }
}
