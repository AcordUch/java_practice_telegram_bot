package practice_telegram_bot.telegram.commands;

public interface Command {
    String formAnswer(); //TODO
    Command execute(Long chatId); //тоже что и formAnswer? //Выполняется и возвращает себя что бы вызвать formAnswer
}
