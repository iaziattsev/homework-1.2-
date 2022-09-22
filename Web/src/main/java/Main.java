import java.io.*;
import java.net.URL;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;
import org.ini4j.Wini;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException, JSONException {
        Scanner sc = new Scanner(System.in);
        Wini ini = new Wini(new File("C:\\Users\\User\\IdeaProjects\\Web\\src\\main\\test.ini"));
        ini.clear();
        System.out.print("Input your character: ");
        String a = sc.next();

        @Deprecated
        JSONObject json = new JSONObject(IOUtils.toString(new URL(String.format("https://www.amiiboapi.com/api/amiibo/?name=%s", a))));

        String[] keys = {"gameSeries", "type", "character"};
        String[] keysCountry = {"au", "eu", "jp", "na"};

        for (String key : keys) {
            ini.put("Information", key, json.getJSONArray("amiibo").getJSONObject(0).get(key));
            ini.store();
        }

        for (String key : keysCountry) {
            ini.put("Release Dates", key, json.getJSONArray("amiibo").getJSONObject(0).getJSONObject("release").get(key));
            ini.store();
        }
        ini.put("image", "image", json.getJSONArray("amiibo").getJSONObject(0).get("image"));
        ini.store();

    }
}