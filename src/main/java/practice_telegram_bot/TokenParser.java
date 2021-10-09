package practice_telegram_bot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class TokenParser {
    public static Map<String, String> GetEnv(){
        var res = new HashMap<String, String>();
        try(BufferedReader reader = Files.newBufferedReader(Path.of("src//main//resources//bot_token.txt"))){
            for (int i = 0; i < 2; i++) {
                var line = reader.readLine().split("=");
                res.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Возможно отсутствует файл с токеном в директории: src/main/resources/bot_token.txt");
        }
        return res;
    }
}