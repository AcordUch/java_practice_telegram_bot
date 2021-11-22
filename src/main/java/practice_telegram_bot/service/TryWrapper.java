package practice_telegram_bot.service;

public class TryWrapper<T> {
    public final boolean presented;
    public final T content;

    public TryWrapper(boolean tryResult, T value){
        this.presented = tryResult;
        this.content = value;
    }
}
