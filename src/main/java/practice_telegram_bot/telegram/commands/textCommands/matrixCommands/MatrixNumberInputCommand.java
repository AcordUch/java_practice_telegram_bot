package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.commands.Command;

public class MatrixNumberInputCommand implements Command {
    public static final String ANSWER = """
            Введите количество матриц в виде одного числа.
            Значение не может быть меньше одного.
            """;

    private String answer = ANSWER;

    @Override
    public String formAnswer() {
        return answer;
    }

    @Override
    public Command execute(Long chatId, String addInfo, UserDB userDBData) {
        try{
            var input = Integer.parseInt(addInfo);
            answer = ChooseOperationsCommand.formAnswerByOperation(userDBData.getMatrixData().getOperation());

            userDBData.getMatrixData().setMatricesNumberToEnter(input);
            userDBData.setState(StateEnum.MATRIX_SIZE_INPUT);
        } catch(NumberFormatException e){
            answer = "Вы ввели некорректное значение, попробуйте ещё раз";
            return this;
        }
        return this;
    }
}
