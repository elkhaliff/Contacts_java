package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    List<Contact> people;

    public PhoneBook() {
        people = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        var id = searchIdByName(contact.getFullName());
        if (id != null)
            people.set(id, contact);
        else
            people.add(contact);
    }

    public int countPeople() { return people.size(); }

    public List<Contact> getPeople() { return people; }

    public Contact getContactById(int cnt) { return people.get(cnt - 1); }

    public void editContactById(int cnt, Contact contact) { people.set(cnt - 1, contact); }

    public void removeContact(int contactId) { people.remove(contactId - 1); }

    private Integer searchIdByName(String fullName) {
        if (countPeople() > 0) {
            for (int i = 0; i < people.size(); i++) {
                if (fullName.equals(people.get(i).getFullName()))
                    return i;
            }
        }
        return null;
    }


}