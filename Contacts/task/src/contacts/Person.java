package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Contact implements Serializable {
    String name, surname, phone;
    LocalDate birthDate;
    String gender;

    public Person(String name, String surname, String birthDate, String gender, String phone) {
        this.isPerson = true;
        this.name = name;
        this.surname = surname;
        setBirthDate(birthDate);
        setGender(gender);
        this.phone = testPhone(phone);
        timeCreated = LocalDateTime.now();
        lastEdit = LocalDateTime.now();
    }

    public void setGender(String gender) {
        if ("M".equals(gender) || "F".equals(gender)) {
            this.gender = gender;
            lastEdit = LocalDateTime.now();
        } else {
            this.gender = "[no data]";
            lastEdit = LocalDateTime.now();
            System.out.println("Bad gender!");
        }
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
            lastEdit = LocalDateTime.now();
        } catch (Exception e) {
            System.out.println("Bad birth date!");
        }
    }

    public void setName(String name) {
        this.name = name;
        lastEdit = LocalDateTime.now();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        lastEdit = LocalDateTime.now();
    }

    public void setPhone(String phone) {
        this.phone = testPhone(phone);
        lastEdit = LocalDateTime.now();
    }

    public String getName() { return name; }
    private String getSurname() { return surname; }

    public String getBirthDate() {
        return (birthDate != null) ? birthDate.toString() : "[no data]";
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + getBirthDate() + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + phone + "\n" +
                "Time created: " + timeCreated + "\n" +
                "Time last edit: " + lastEdit + "\n";
    }

    @Override
    public String getFullName() {
        return String.format("%s %s", getName(), getSurname());
    }
}