package practice_telegram_bot.telegram;

import practice_telegram_bot.GlobalConst;
import practice_telegram_bot.matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MatrixImageCreator {
    private static MatrixImageCreator instance = null;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 450;
    private static final int SHIFT = 16;
    private static final double HEIGHT_FACTOR = 1.3;
    private static final double HEIGHT_SHIFT = SHIFT * HEIGHT_FACTOR;
    private int canvasWidth = DEFAULT_WIDTH;
    private int canvasHeight = DEFAULT_HEIGHT;

    public static MatrixImageCreator instance(){
        if(instance == null){
            instance = new MatrixImageCreator();
        }
        return instance;
    }

    private MatrixImageCreator(){}

    public File getImage(){
        return new File(GlobalConst.WAY_TO_MATRIX_IMAGE);
    }

    public MatrixImageCreator createImage(Matrix rawMatrix){
        var matrix = prepareText(rawMatrix);
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
        return this;
    }

    private String[] prepareText(Matrix rawMatrix){
        var cellSize = determineSizeOfMatrixCells(rawMatrix);
        var result = new String[rawMatrix.getVerticalSize()];
        var rowLength = Arrays.stream(cellSize).sum();
        var rowBuilder = new StringBuilder(rowLength);
        for(int row = 0; row < rawMatrix.getVerticalSize(); row++){
            for(int column = 0; column < rawMatrix.getHorizontalSize(); column++){
                var element = String.format("%,.3f", rawMatrix.getElement(row, column));
                rowBuilder
                        .append(buildString(' ', cellSize[column] - element.length()))
                        .append(element)
                        .append(' ');
            }
            result[row] = rowBuilder.toString();
            rowBuilder = new StringBuilder(rowLength);
        }
        return result;
    }

    private int[] determineSizeOfMatrixCells(Matrix rawMatrix){
        var cellSize = new int[rawMatrix.getHorizontalSize()];
        for(int row = 0; row < rawMatrix.getVerticalSize(); row++){
            for(int column = 0; column < rawMatrix.getHorizontalSize(); column++){
                cellSize[column] = Math.max(
                        cellSize[column],
                        String.format("%,.3f", rawMatrix.getElement(row, column)).length()
                );
            }
        }
        return cellSize;
    }

    private String buildString(char c, int n) {
        char[] arr = new char[n];
        Arrays.fill(arr, c);
        return new String(arr);
    }

    private void setupCanvasSize(String[] key){
        canvasWidth = SHIFT + key[0].length() * SHIFT;
        canvasHeight = (int)(HEIGHT_SHIFT + key.length * HEIGHT_SHIFT);
    }

    private void prepareTheCanvas(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    private void typeMatrix(Graphics graphics, String[] key){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25)); //"PT Sans"
        var x = 10;
        var y = 25;
        for(var line : key){
            graphics.drawString(line, x, y);
            y += HEIGHT_SHIFT;
        }
    }
}
