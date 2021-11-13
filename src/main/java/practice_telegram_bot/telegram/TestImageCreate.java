package practice_telegram_bot.telegram;

import practice_telegram_bot.GlobalConst;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestImageCreate {
    public static void createImage(String key){
        BufferedImage bufferedImage = new BufferedImage(350, 70,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 350, 70);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 10));
        graphics.drawString(key, 10, 25);
        try {
            ImageIO.write(bufferedImage, "jpg", new File(
                    GlobalConst.WAY_TO_MATRIX_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Image Created");
    }
}
