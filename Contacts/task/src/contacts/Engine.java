package contacts;

import java.util.List;
import java.util.Scanner;

public class Engine {
    final String menu = "Enter action (add, remove, edit, count, info, exit): ";
    private final PhoneBook phoneBook;
    Action action;
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
        boolean ret = true;
        while (ret) {
            var inp = getString(menu).toUpperCase();
            action = Action.valueOf(inp);
            switch (action) {
                case ADD -> addContact();
                case REMOVE -> removeContact();
                case EDIT -> editContact();
                case COUNT -> System.out.printf("The Phone Book has %d records.%n", phoneBook.countPeople());
                case INFO -> showList();
                case EXIT -> ret = false;
            }
            /*if (Action.ADD.getAction().equals(action))  addContact();
            else if (Action.REMOVE.getAction().equals(action))  removeContact();
            else if (Action.EDIT.getAction().equals(action))  editContact();
            else if (Action.COUNT.getAction().equals(action))
                System.out.printf("The Phone Book has %d records.%n", phoneBook.countPeople());
            else if (Action.INFO.getAction().equals(action))  showList();
            else if (Action.EXIT.getAction().equals(action))  return;*/
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
            int personId = Integer.parseInt(getString("Select a record: "));
            Person person = phoneBook.getPersonById(personId);
            var inp = getString("Select a field (name, surname, number): ").toUpperCase();
            action = Action.valueOf(inp);
            switch (action) {
                case NAME -> person.setName(getString("Enter name: "));
                case SURNAME -> person.setSurname(getString("Enter surname: "));
                case NUMBER -> person.setPhone(getString("Enter surname: "));
            }
            phoneBook.editPersonById(personId, person);
            /*if ("name".equals(action)) person.setName(getString("Enter name: "));
            else if ("surname".equals(action)) person.setSurname(getString("Enter surname: "));
            else if ("number".equals(action)) person.setPhone(getString("Enter surname: "));
            phoneBook.editPersonById(personId, person);*/
        } else
            System.out.println("No records to edit!");
    }

    private void removeContact() {
        if (phoneBook.countPeople() > 0) {
            int personId = Integer.parseInt(getString("Select a record: "));
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