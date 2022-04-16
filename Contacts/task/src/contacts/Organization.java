package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Organization extends Contact implements Serializable {
    String address;

    public Organization(String name, String address, String phone) {
        this.isPerson = false;
        this.name = name;
        this.address = address;
        this.phone = testPhone(phone);
        timeCreated = LocalDateTime.now();
        lastEdit = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
        lastEdit = LocalDateTime.now();
    }

    public void setAddress(String address) {
        this.address = address;
        lastEdit = LocalDateTime.now();
    }

    public void setPhone(String phone) {
        this.phone = testPhone(phone);
        lastEdit = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Organization name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Number: " + phone + "\n" +
                "Time created: " + timeCreated + "\n" +
                "Time last edit: " + lastEdit + "\n";
    }

    @Override
    public String getFullName() { return getName(); }

    private String getName() { return name; }
}
