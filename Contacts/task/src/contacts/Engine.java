package contacts;

import java.util.List;
import java.util.Scanner;

public class Engine {
    final String menu = "Enter action (add, remove, edit, count, list, exit): ";
    private final PhoneBook phoneBook;
    String action;
    int cnt;

    public Engine() {
        phoneBook = new PhoneBook();
    }

    private String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }

    public void run() {
        while (true) {
            action = getString(menu);
            if ("add".equals(action))  addContact();
            else if ("remove".equals(action))  removeContact();
            else if ("edit".equals(action))  editContact();
            else if ("count".equals(action))
                System.out.printf("The Phone Book has %d records.%n", phoneBook.countPeople());
            else if ("list".equals(action))  showList();
            else if ("exit".equals(action))  return;
        }
    }

    private void showList() {
        if (phoneBook.countPeople() > 0) {
            List<Person> people = phoneBook.getPeople();
            String format = "%d. %s %s, %s";
            cnt = 1;
            people.forEach(p -> System.out.printf((format) + "%n", cnt++, p.getName(), p.getSurname(), p.getPhone()));
        } else
            System.out.println("No records to list!");
    }

    private void editContact() {
        if (phoneBook.countPeople() > 0) {
            action = getString("Select a record: ");
            int personId = Integer.parseInt(action);
            Person person = phoneBook.getPersonById(personId);
            action = getString("Select a field (name, surname, number): ");
            if ("name".equals(action)) person.setName(getString("Enter name: "));
            else if ("surname".equals(action)) person.setSurname(getString("Enter surname: "));
            else if ("number".equals(action)) person.setPhone(getString("Enter surname: "));
            phoneBook.editPersonById(personId, person);
        } else
            System.out.println("No records to edit!");
    }

    private void removeContact() {
        if (phoneBook.countPeople() > 0) {
            action = getString("Select a record: ");
            int personId = Integer.parseInt(action);
            phoneBook.removePerson(personId);
        } else
            System.out.println("No records to remove!");
    }

    private void addContact() {
        String name = getString("Enter the name:");
        String surname = getString("Enter the surname:");
        String number = getString("Enter the number:");
        Person person = new Person(name, surname, number);
        phoneBook.addPerson(person);
        System.out.println("The record added.");
    }
}