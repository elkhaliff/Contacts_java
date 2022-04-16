package contacts;

import java.util.List;
import java.util.Scanner;

public class Engine {
    final String SERIAL_FILE_NAME = "phonebook.db";

    final String menu = "[menu] Enter action (add, list, search, count, exit): ";
    private final PhoneBook phoneBook;
    PhoneBook searchBook;
    int cnt;

    public Engine() {
        phoneBook = new PhoneBook();
    }

    private String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }

    private int getInt(String strAction) {
        int ret;
        try {
            ret = Integer.parseInt(strAction);
        } catch (Exception e) {
            ret = 0;
        }
        return ret;
    }

    private Action getAction(String message) {
        var inp = getString(message).toUpperCase();
        return Action.valueOf(inp);
    }

    public void run() {
        /*try {
            phoneBook = (PhoneBook) SerializationUtils.deserialize(SERIAL_FILE_NAME);
            System.out.println("open " + SERIAL_FILE_NAME + "\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("");
        } */
        System.out.println("open " + SERIAL_FILE_NAME + "\n");

        boolean ret = true;
        while (ret) {
            switch (getAction(menu)) {
                case ADD -> addContact();
                case LIST -> list();
                case SEARCH -> searchContact();
                case COUNT -> System.out.printf("The Phone Book has %d records.%n", phoneBook.countPeople());
                case EXIT -> {
                  /*  try {
                        SerializationUtils.serialize(phoneBook, SERIAL_FILE_NAME);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    ret = false;
                }
            }
        }
    }

    private void addContact() {
        switch (getAction("Enter the type (person, organization): ")) {
            case PERSON -> addPerson();
            case ORGANIZATION -> addOrganization();
        }
    }

    private void addPerson() {
        String name = getString("Enter the name: ");
        String surname = getString("Enter the surname: ");
        String birthDate = getString("Enter the birth date: ");
        String gender = getString("Enter the gender (M, F): ");
        String number = getString("Enter the number: ");
        Person person = new Person(name, surname, birthDate, gender, number);
        phoneBook.addContact(person);
        System.out.println("The record added.\n");
    }

    private void addOrganization() {
        String name = getString("Enter the organization name: ");
        String address = getString("Enter the address: ");
        String number = getString("Enter the number: ");
        Organization organization = new Organization(name, address, number);
        phoneBook.addContact(organization);
        System.out.println("The record added.\n");
    }

    private void list() {
        if (showList() > 0) {
            String strAction = getString("[list] Enter action ([number], back): ");
            int recordId = getInt(strAction);
            if (recordId > 0) showRecord(recordId, false);
        }
    }

    private int showList() {
        int count = phoneBook.countPeople();
        if (count > 0) {
            List<Contact> people =  phoneBook.getPeople();
            String format = "%d. %s";
            cnt = 1;
            people.forEach(p -> System.out.printf((format) + "%n", cnt++, p.getFullName()));
        } else
            System.out.println("No records to list!");

        System.out.println();
        return count;
    }

    private void searchContact() {
        String strAction = "AGAIN";
        boolean ret = true;
        int recordId;
        do {
            recordId = getInt(strAction);

            if (recordId == 0) {
                Action action = Action.valueOf(strAction);
                switch (action) {
                    case BACK -> ret = false;
                    case AGAIN -> searchContact(getString("Enter search query: "));
                }
                strAction = getString("[search] Enter action ([number], back, again): ");
            } else {
                showRecord(recordId, true);
                ret = false;
            }
        } while (ret);
    }

    private void searchContact(String searchStr) {
        searchBook = new PhoneBook();
        if (phoneBook.countPeople() > 0) {
            List<Contact> people =  phoneBook.getPeople();
            cnt = 1;
            for (Contact contact : people) {
                if (contact.getFullName().toLowerCase().contains(searchStr.toLowerCase()) ||
                        contact.getPhone().contains(searchStr))
                    searchBook.addContact(contact);
            }
            if (searchBook.countPeople() > 0) {
                String format = "%d. %s";
                searchBook.getPeople().forEach(p -> System.out.printf((format) + "%n", cnt++, p.getFullName()));
            } else
                System.out.println("No records to list!");
        }
        System.out.println();
    }

    private void showRecord(int recordId, boolean isSearch) {
        boolean ret = true;
        PhoneBook contacts = isSearch ? searchBook : phoneBook;
        while (ret) {
            System.out.println(contacts.getContactById(recordId));
            switch (getAction("[record] Enter action (edit, delete, menu): ")) {
                case EDIT -> editContact(recordId);
                case DELETE -> { deleteContact(recordId); ret = false; }
                case MENU -> ret = false;
            }
        }
        System.out.println();
    }

    private void editContact(int recordId) { // переделать на Builder
        Contact contact = phoneBook.getContactById(recordId);
        if (contact.isPerson) {
            Person person = (Person) contact;
            switch (getAction("Select a field (name, surname, birth, gender, number): ")) {
                case NAME -> person.setName(getString("Enter name: "));
                case SURNAME -> person.setSurname(getString("Enter surname: "));
                case BIRTH -> person.setBirthDate(getString("Enter birth date: "));
                case GENDER -> person.setGender(getString("Enter birth gender: "));
                case NUMBER -> person.setPhone(getString("Enter surname: "));
            }
            phoneBook.editContactById(recordId, person);
        } else {
            Organization organization = (Organization) contact;
            switch (getAction("Select a field (name, address, number): ")) {
                case NAME -> organization.setName(getString("Enter name: "));
                case ADDRESS -> organization.setAddress(getString("Enter address: "));
                case NUMBER -> organization.setPhone(getString("Enter surname: "));
            }
            phoneBook.editContactById(recordId, organization);
        }
        System.out.println("The record updated!\n");
    }

    private void deleteContact(int recordId) {
        phoneBook.removeContact(recordId);
        System.out.println("The record deleted!\n");
    }
}