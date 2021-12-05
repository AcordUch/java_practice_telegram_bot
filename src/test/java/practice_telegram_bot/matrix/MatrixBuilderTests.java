package practice_telegram_bot.matrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixBuilderTests {
    private MatrixBuilder builder;

    @Before
    public void setUp(){
        builder = new MatrixBuilder();
    }

    @After
    public void afterTest(){
        builder = null;
    }

    @Test
    public void createSimpleMatrix() throws IncorrectNumberOfElements {
        var expected = "1.0 1.0\n0.0 0.0\n";
        builder.createNewMatrix(2)
                .addRow("1 1")
                .addRow("0 0");

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void createSimpleMatrix2() throws IncorrectNumberOfElements {
        var expected = "2.0 2.1 2.2\n1.0 1.1 1.2\n0.0 0.1 0.2\n";
        builder.createNewMatrix(3)
                .addRow("2.0 2.1 2.2")
                .addRow("1.0 1.1 1.2")
                .addRow("0.0 0.1 0.2");

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void incompleteCreation() throws IncorrectNumberOfElements {
        var expected = "2.0 2.0\n1.0 1.0\n0.0 0.0\n";
        builder.createNewMatrix(3, 2)
                .addRow("2 2")
                .addRow("1 1");

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void overflowingCreation() throws IncorrectNumberOfElements {
        var expected = "2.0 2.0\n1.0 1.0\n";
        builder.createNewMatrix(2, 2)
                .addRow("2 2")
                .addRow("1 1")
                .addRow("0 0");

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void tooMuchOverflowingCreation() throws IncorrectNumberOfElements {
        var expected = "0.0 0.0\n1.0 1.0\n2.0 2.0\n";
        builder.createNewMatrix(3, 2);
        for(var i = 0; i < 50; i++){
            builder.addRow(String.format("%d %d", i, i));
        }

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void creatMatrixFromTemplate() throws IncorrectNumberOfElements {
        var expected = "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n";
        builder = new MatrixBuilder(
                "4 4",
                "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n"
                );

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void incompleteCreationFromTemplate() throws IncorrectNumberOfElements {
        var expected = "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n0.0 0.0 0.0 0.0\n";
        builder = new MatrixBuilder(
                "4 4",
                "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n"
        );

        assertEquals(expected, builder.buildMatrix().toString());
    }

    @Test
    public void overflowingCreationFromTemplate() throws IncorrectNumberOfElements {
        var expected = "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n";
        builder = new MatrixBuilder(
                "3 4",
                "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n5.0 5.0 5.0 5.0\n"
        );

        assertEquals(expected, builder.buildMatrix().toString());
    }
}
