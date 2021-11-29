package practice_telegram_bot;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TokenParserTest {

    public static Map<String, String> overriddenGetEnv(String filePath){
        var res = new HashMap<String, String>();
        try(BufferedReader reader = Files.newBufferedReader(Path.of(filePath))){
            for (int i = 0; i < 2; i++) {
                var line = reader.readLine().split("=");
                res.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Возможно отсутствует файл с токеном в директории: " + filePath);
        }
        return res;
    }

    @Test
    void getEnvTest() {
        var realResult = overriddenGetEnv("src/test/resources/test_bot_token.txt");
        var expectedResult = new HashMap<String, String>();

        expectedResult.put("BOT_NAME", "java_bot");
        expectedResult.put("BOT_TOKEN", "2096");

        assertEquals(realResult, expectedResult);
    }

    @Test
    void getEnvTest2() {
        var realResult = overriddenGetEnv("src/test/resources/test_bot_token_2.txt");
        var expectedResult = new HashMap<String, String>();

        expectedResult.put("BOT_NAME", "project_bot");
        expectedResult.put("BOT_TOKEN", "3984567.2346");

        assertEquals(realResult, expectedResult);
    }

    @Test
    void getEnvTest3() {
        var realResult = overriddenGetEnv("src/test/resources/test_bot_token_3.txt");
        var expectedResult = new HashMap<String, String>();

        expectedResult.put("BOT_NAME", "something");
        expectedResult.put("BOT_TOKEN", "3456dgfh46");

        assertEquals(realResult, expectedResult);
    }

    @Test
    void getEnvTestReverse() {
        var realResult = overriddenGetEnv("src/test/resources/test_bot_token_reverse.txt");
        var expectedResult = new HashMap<String, String>();

        expectedResult.put("i_am_bot", "BOT_NAME");
        expectedResult.put("312353635", "BOT_TOKEN");

        assertEquals(realResult, expectedResult);
    }

    @Test
    void getEnvTestEmpty() {
        assertThrows(NullPointerException.class, ()-> overriddenGetEnv(
                "src/test/resources/test_bot_token_empty.txt"));
    }

    @Test
    void getEnvTestNoParams() {
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> overriddenGetEnv(
                "src/test/resources/test_bot_token_no_params.txt"));
    }

    @Test
    void getEnvTestNoDelimiter() {
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> overriddenGetEnv(
                "src/test/resources/test_bot_token_no_delimiter.txt"));
    }
}