package practice_telegram_bot.service;

public class outWrapper<T> {
    private T ref;

    public outWrapper(T value){
        ref = value;
    }

    public T get(){
        return ref;
    }

    public void set(T value){
        ref = value;
    }

    public String toString(){
        return ref.toString();
    }
}
