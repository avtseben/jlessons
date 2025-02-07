package step1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cli app started");

        String [] users = {"Alex", "Petr", "Sergey"};
        String [] myContacts = {};
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            if (nextLine.startsWith("/search")) {
                //Search mode
                String searchQuery = nextLine.split(" ")[1];
                String foundUser = null;
                for (String user : users) {
                    if (user.equals(searchQuery)) {
                        foundUser = user;
                    }
                }

                if (foundUser != null) {
                    System.out.println("found: " + foundUser);
                } else {
                    System.out.println("user not founded");
                }
            } else if (nextLine.startsWith("/list")) {
                //List contacts
                if (myContacts.length == 0) {
                    System.out.println("no contacts yet");
                } else {
                    System.out.println("my contacts:");
                    for (String contact : myContacts) {
                        System.out.println(contact);
                    }
                }
            } else {
                System.out.println("echo: " + nextLine);
            }
        }
    }
}
