package lesson2.practice1;

import lesson2.practice1.mode.ModeHolder;
import lesson2.practice1.out.TerminalOutput;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CliApplication {

    private ModeHolder mode;
    private TerminalOutput out;

    public void start() {
        mode = new ModeHolder();
        out = new TerminalOutput(mode);

        out.print("Cli app started");


        String[] contactList = new String[3];

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            if ("".equals(nextLine)) {
                continue;
            }

            if (nextLine.startsWith("/list")) {
                if (isEmpty(contactList)) {
                    out.print("No users yet");
                } else {
                    StringBuffer result = new StringBuffer();
                    result.append("users:\n\r");
                    for (String user : contactList) {
                        if (user != null) {
                            result.append(" - " + user + "\n\r");
                        }
                    }
                    out.print(result.toString());
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
                    out.print(userToAdd + " added to contact list");
                } else {
                    out.print("User already added");
                }

            } else if (nextLine.startsWith("/send")) {
                // todo validate empty
                Pattern pattern = Pattern.compile("^/send +(?<user>\\w+) +(?<message>.*)");
                Matcher matcher = pattern.matcher(nextLine);
                if (matcher.find()) {
                    String user = matcher.group("user");
                    // todo validate user exists
                    String message = matcher.group("message");
                    out.print("echo from " + user + ": " + message);
                }
            } else if (nextLine.startsWith("/go")) {
                Pattern pattern = Pattern.compile("^/go +(?<user>\\w+)");
                Matcher matcher = pattern.matcher(nextLine);
                if (matcher.find()) {
                    String user = matcher.group("user");
                    // todo validate user exists
                    mode.connectToUser(user);
                    out.print("Connected to " + user);
                }
            } else if (nextLine.startsWith("/q")) {
                if (mode.isDefaultMode()) {
                    out.print("Already in default");
                }

                if (mode.isConnected()) {
                    mode.toDefault();
                    out.print("Back to default");
                }

            } else if (nextLine.startsWith("/exit")) {
                out.print("Buy!");
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
                out.print(help);
            } else {
                if (mode.isConnected()) {
                    out.print("echo from " + mode.getConnectedUser() +": " + nextLine);
                } else {
                    out.print("echo: " + nextLine);
                }
            }
        }
    }

    private String[] addUser(String[] users, String userToAdd) {
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

    private boolean isEmpty(String[] users) {
        for (String user : users) {
            if (user != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isFull(String[] users) {
        for (String user : users) {
            if (user == null) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("ManualArrayCopy")
    private String[] grow(String[] origin) {
        int newSize = origin.length * 2;
        String[] newArray = new String[newSize];
        for (int i = 0; i < origin.length; i++) {
            newArray[i] = origin[i];
        }
        return newArray;
    }

}
