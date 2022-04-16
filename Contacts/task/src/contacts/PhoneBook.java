package contacts;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    @Serial
    private static final long serialVersionUID = 101L;

    List<Contact> people;

    public PhoneBook() {
        people = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        people.add(contact);
    }

    public int countPeople() { return people.size(); }

    public List<Contact> getPeople() { return people; }

    public Contact getContactById(int cnt) { return people.get(cnt - 1); }

    public void editContactById(int cnt, Contact contact) { people.set(cnt - 1, contact); }

    public void removeContact(int contactId) { people.remove(contactId - 1); }
}