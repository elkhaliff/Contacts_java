package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    String phone;
    String name;
    LocalDateTime timeCreated;
    LocalDateTime lastEdit;
    boolean isPerson;

    public abstract String getFullName();

    public String getPhone() { return phone != null ? phone : ""; }
    
    protected String testPhone(String phone) {
        String test = phone;
        Pattern patternMin =  Pattern.compile("^([+]?[0-9]?[ \\-]?)?((\\(?[\\w]{2,}\\)?[ \\-]?)?([\\w]{2,})?|([\\w]{2,}?[ \\-]?)?(\\(?[\\w]{2,}?\\)?)?)?([ \\-]?([\\w]{2,}))*$");
        if (phone.length() == 0 || !patternMin.matcher(phone).find()) {
            System.out.println("Wrong number format!");
            test = "[no number]";
        }
        return test;
    }
}