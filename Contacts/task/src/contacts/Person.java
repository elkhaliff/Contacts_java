package contacts;

import java.util.regex.Pattern;

public class Person extends Contact{
    String name, surname, phone;

    public Person(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = testPhone(phone);
    }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setPhone(String phone) { this.phone = testPhone(phone); }

    private String testPhone(String phone) {
        String test = phone;
        Pattern patternMin =  Pattern.compile("^([+]?[0-9]?[ \\-]?)?((\\(?[\\w]{2,}\\)?[ \\-]?)?([\\w]{2,})?|([\\w]{2,}?[ \\-]?)?(\\(?[\\w]{2,}?\\)?)?)?([ \\-]?([\\w]{2,}))*$");
        if (!patternMin.matcher(phone).find()) {
            System.out.println("Wrong number format!");
            test = "[no number]";
        }
        return test;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getPhone() { return phone; }

    @Override
    void getData() {

    }

    @Override
    void setData() {

    }
}