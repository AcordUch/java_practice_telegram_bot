package practice_telegram_bot.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixToStringTests {
    @Test
    public void size1_1Test(){
        var expected = "1.0\n";
        var matrix = new Matrix(
                new double[] { 1 }
        );

        assertEquals(expected, matrix.toString());
    }

    @Test
    public void size2_2Test(){
        var expected = "1.0 1.0\n2.0 2.0\n";
        var matrix = new Matrix(
                new double[] { 1, 1 },
                new double[] { 2, 2 }
        );

        assertEquals(expected, matrix.toString());
    }

    @Test
    public void size3_3Test(){
        var expected = "1.0 1.5 1.0\n2.0 2.5 2.0\n3.0 3.5 3.0\n";
        var matrix = new Matrix(
                new double[] { 1, 1.5, 1 },
                new double[] { 2, 2.5, 2 },
                new double[] { 3, 3.5, 3 }
        );

        assertEquals(expected, matrix.toString());
    }

    @Test
    public void size2_4Test(){
        var expected = "1.1 1.2 1.3 1.4\n2.0 2.1 2.2 2.3\n";
        var matrix = new Matrix(
                new double[] { 1.1, 1.2, 1.3, 1.4 },
                new double[] { 2.0, 2.1, 2.2, 2.3 }
        );

        assertEquals(expected, matrix.toString());
    }

    @Test
    public void nonSameRowTest(){
        var expected = "1.1 1.2 1.3 1.4\n2.0 2.1\n3.1 3.2 3.3\n";
        var matrix = new Matrix(
                new double[] { 1.1, 1.2, 1.3, 1.4 },
                new double[] { 2.0, 2.1 },
                new double[] { 3.1, 3.2, 3.3 }
        );

        assertEquals(expected, matrix.toString());
    }
}
