package practice_telegram_bot.telegram;

import practice_telegram_bot.GlobalConst;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MatrixImageCreate {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 450;
    private static final int SHIFT = 16;
    private static final double HEIGHT_FACTOR = 1.3;
    private int canvasWidth = DEFAULT_WIDTH;
    private int canvasHeight = DEFAULT_HEIGHT;
    public void createImage(String key){
        var matrix = prepareText(key);
        setupCanvasSize(matrix);
        BufferedImage bufferedImage = new BufferedImage(canvasWidth, canvasHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        prepareTheCanvas(graphics);
        typeMatrix(graphics, matrix);
        try {
            ImageIO.write(bufferedImage, "jpg", new File(
                    GlobalConst.WAY_TO_MATRIX_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Image Created");
    }

    private String[] prepareText(String key){
        var res = key.split("\n");
        return res;
    }

    private void setupCanvasSize(String[] key){
        canvasWidth = SHIFT + key[0].length() * SHIFT;
        canvasHeight = (int)(HEIGHT_FACTOR * SHIFT + key.length * HEIGHT_FACTOR * SHIFT);
    }

    private void prepareTheCanvas(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    private void typeMatrix(Graphics graphics, String[] key){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 25));
        var x = 10;
        var y = 25;
        for(var line : key){
            graphics.drawString(line, x, y);
            y += SHIFT * HEIGHT_FACTOR;
        }
    }
}
