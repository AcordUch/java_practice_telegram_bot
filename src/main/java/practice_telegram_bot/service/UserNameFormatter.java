package practice_telegram_bot.service;

import org.telegram.telegrambots.meta.api.objects.User;

public class UserNameFormatter {
    public final String nickname;
    public final String firstName;
    public final String lastName;

    public UserNameFormatter(String nickname, String firstName, String lastName) {
        this.nickname = nickname != null ? nickname : "";
        this.firstName = firstName != null ? firstName : "";
        this.lastName = lastName != null ? lastName : "";
    }

    public UserNameFormatter(User user){
        this(user.getUserName(), user.getFirstName(), user.getLastName());
    }

    public String formFullName(){
        return formFullName(" ");
    }

    public String formFullName(String separator){
        return String.format("%s%s%s%s%s", nickname, separator, firstName, separator, lastName);
    }
}
