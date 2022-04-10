package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    List<Person> people;

    public PhoneBook() {
        people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public int countPeople() { return people.size(); }

    public List<Person> getPeople() { return people; }

    public Person getPersonById(int cnt) { return people.get(cnt - 1); }

    public void editPersonById(int cnt, Person person) { people.set(cnt - 1, person); }

    public void removePerson(int personId) { people.remove(personId - 1); }
}