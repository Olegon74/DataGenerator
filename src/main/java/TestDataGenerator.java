import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestDataGenerator {

    public static void main(String[] args) {
        List<Contact> contacts = generateContacts(8);
        saveContactsToJson(contacts, "contacts.json");
        List<Group> groups = generateGroups(4, contacts);
        saveGroupsToJson(groups, "groups.json");
    }

    private static final String[] FIRST_NAMES = {"Александр", "Михаил", "Сергей", "Андрей", "Анна", "Елена", "Олег"};
    private static final String[] LAST_NAMES = {"Смирнов", "Сидоров", "Петров", "Васильев", "Михайлова", "Соколов"};
    private static final String[] PATRONYMIC_NAMES = {"Александрович", "Михайлович", "Сергеевич", "Андреевич"};
    private static final String[] GROUP_NAMES = {"Категория здоровья А", "Категория здоровья Б", "Категория здоровья В", "Категория здоровья Г"};

    private static final String[] ADDRESSES = {
            "ул. Гагарина д.15 кв.45",
            "ул. Барбюса д.140 кв.83",
            "ул. Агалакова д.62 кв.12",
            "ул. Машиностроителей д.100 кв.11",
            "ул. Новороссийская д.55 кв.38",
            "ул. Дзержинского д.77 кв.19"
    };

    private static List<Contact> generateContacts(int count) {
        List<Contact> generatedContacts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String fullName = generateFullName();
            String address = generateAddress();
            String phoneNumber = generatePhoneNumber();
            String clientId = generateClientId();

            Contact contact = new Contact(fullName, address, phoneNumber, clientId);
            generatedContacts.add(contact);
        }

        return generatedContacts;
    }

    private static List<Group> generateGroups(int count, List<Contact> contacts) {
        List<Group> generatedGroups = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String name = generateGroupName();
            List<Contact> members = generateGroupMembers(contacts);

            Group group = new Group(name, members);
            generatedGroups.add(group);
        }

        return generatedGroups;
    }

    public static String generateFullName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String patronymic = PATRONYMIC_NAMES[random.nextInt(PATRONYMIC_NAMES.length)];

        return lastName + " " + firstName + " " + patronymic;
    }

    private static String generateAddress() {
        int index = (int) (Math.random() * ADDRESSES.length);
        return ADDRESSES[index];
    }

    private static String generatePhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder();

        phoneNumber.append("+");
        phoneNumber.append(generateRandomNumber(1, 9));
        phoneNumber.append(generateRandomNumber(100, 999));
        phoneNumber.append(generateRandomNumber(1000000, 9999999));

        return phoneNumber.toString();
    }

    private static int generateRandomNumber(int minValue, int maxValue) {
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

    private static String generateClientId() {
        return UUID.randomUUID().toString();
    }

    private static String generateGroupName() {
        int index = (int) (Math.random() * GROUP_NAMES.length);
        return GROUP_NAMES[index];
    }

    private static List<Contact> generateGroupMembers(List<Contact> contacts) {
        List<Contact> generatedGroupMembers = new ArrayList<>();

        for (Contact contact : contacts) {
            generatedGroupMembers.add(contact);
        }

        return generatedGroupMembers;
    }

    private static void saveContactsToJson(List<Contact> contacts, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(contacts);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveGroupsToJson(List<Group> groups, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(groups);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
