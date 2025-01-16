import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cli app started");

        String [] users = {"Alex", "Petr", "Sergey"};
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            if (nextLine.startsWith("/s")) {
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
            } else {
                System.out.println("echo: " + nextLine);
            }
        }
    }
}
