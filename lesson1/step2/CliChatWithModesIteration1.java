package lesson1.step2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CliChatWithModesIteration1 {
    public static final String DEFAULT_MODE = "DEFAULT";
    public static final String INTERCONNECT_MODE = "INTERCONNECT";

    public static void main(String[] args) {
        System.out.println("Cli app started");

        String[] contactList = new String[3];
        String currentMode = DEFAULT_MODE;
        String connectedUser = null;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            if ("".equals(nextLine)) {
                continue;
            }

            if (nextLine.startsWith("/list")) {
                if (isEmpty(contactList)) {
                    System.out.println("No users yet");
                } else {
                    System.out.println("users:");
                    for (String user : contactList) {
                        if (user != null) {
                            System.out.println(" - " + user);
                        }
                    }
                }
            } else if (nextLine.startsWith("/add")) {
                // validate empty
                String userToAdd = nextLine.split(" ")[1];
                // is allReady added
                boolean allreadyExists = false;
                for (String user : contactList) {
                    if (userToAdd.equals(user)) {
                        allreadyExists = true;
                        break;
                    }
                }

                if (!allreadyExists) {
                    contactList = addUser(contactList, userToAdd);
                    System.out.println(userToAdd + " added to contact list");
                } else {
                    System.out.println("User already added");
                }

            } else if (nextLine.startsWith("/send")) {
                // todo validate empty
                Pattern pattern = Pattern.compile("^/send\s+(?<user>\\w+)\s+(?<message>.*)");
                Matcher matcher = pattern.matcher(nextLine);
                if (matcher.find()) {
                    String user = matcher.group("user");
                    // todo validate user exists
                    String message = matcher.group("message");
                    System.out.printf("echo from %s: '%s'\n\r", user, message);
                }
            } else if (nextLine.startsWith("/go")) {
                Pattern pattern = Pattern.compile("^/go\s+(?<user>\\w+)");
                Matcher matcher = pattern.matcher(nextLine);
                if (matcher.find()) {
                    String user = matcher.group("user");
                    // todo validate user exists
                    currentMode = INTERCONNECT_MODE;
                    // todo change prompt
                    connectedUser = user;
                    System.out.printf("Connected to %s'\n\r", user);
                }
            } else if (nextLine.startsWith("/q")) {
                if (DEFAULT_MODE.equals(currentMode)) {
                    System.out.println("Already in default");
                }

                if (INTERCONNECT_MODE.equals(currentMode)) {
                    currentMode = DEFAULT_MODE;
                    connectedUser = null;
                    System.out.println("Back to default");
                }

            } else if (nextLine.startsWith("/exit")) {
                System.out.println("Buy!");
                System.exit(0);

            } else if (nextLine.startsWith("/help")) {
                String help = """
                        Available commands:
                         /list - list users from your contact list
                         /add - add user to contact list
                         /send <user> <message> - to send direct message to the user
                         /go <user> - go to permanent conversation with to the user
                         /q - quit to default state
                         /exit - exit program
                        """;
                System.out.println(help);
            } else {
                if (INTERCONNECT_MODE.equals(currentMode)) {
                    System.out.printf("echo from %s: '%s'\n\r", connectedUser, nextLine);
                } else {
                    System.out.println("echo: " + nextLine);
                }
            }
        }
    }

    private static String[] addUser(String[] users, String userToAdd) {
        if (isFull(users)) {
            users = grow(users);
        }

        for (int i = 0; i < users.length; i++) {
            String user = users[i];
            if (user == null) {
                users[i] = userToAdd;
                return users;
            }
        }

        return users;
    }

    private static boolean isEmpty(String[] users) {
        for (String user : users) {
            if (user != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFull(String[] users) {
        for (String user : users) {
            if (user == null) {
                return false;
            }
        }
        return true;
    }

    private static String[] grow(String[] origin) {
        int newSize = origin.length * 2;
        String[] newArray = new String[newSize];
        for (int i = 0; i < origin.length; i++) {
            newArray[i] = origin[i];
        }
        return newArray;
    }
}