package lesson2.practice3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiConnectSocketServer {

    private ServerSocket serverSocket;
    private RendezvousPoint rendezvousPoint;

    public static void main(String[] args) throws IOException {
        new MultiConnectSocketServer().start();
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(9000);
        rendezvousPoint = new RendezvousPoint();
        System.out.println("Server is listening connections on port " + 9000);

        while (true) {
            Socket accept = serverSocket.accept();
            new ConnectionHandler(accept, rendezvousPoint).start();
        }
    }

    static class ConnectionHandler extends Thread {

        private static final Pattern LOGIN_PATTERN = Pattern.compile("^/login +(?<talkersLogin>\\w+)");
        private static final Pattern SEND_PATTERN = Pattern.compile("^/send +(?<talker>\\w+) +(?<message>.*)");

        private boolean loggedIn = false;

        private final Socket clientSocket;
        private final RendezvousPoint rendezvousPoint;
        private BufferedReader in;
        private PrintWriter out;

        public ConnectionHandler(Socket clientSocket, RendezvousPoint rendezvousPoint) {
            this.clientSocket = clientSocket;
            this.rendezvousPoint = rendezvousPoint;
        }

        @Override
        public void run() {
            try {
                System.out.println("-- Client connected");
                // Каналы взаимодействия
                // от клиента
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // к клиенту
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println("Hi! Tell me your login first please :)..");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.startsWith("/login")) {
                        Matcher matcher = LOGIN_PATTERN.matcher(inputLine);
                        if (matcher.find()) {
                            String login = matcher.group("talkersLogin");
                            rendezvousPoint.goIn(login, in, out);
                            loggedIn = true;
                            out.println("Welcome " + login);
                            continue;
                        }
                    }

                    if (!loggedIn) {
                        out.println("Enter your login with '/login' comand");
                        continue;
                    }

                    if (inputLine.startsWith("/send")) {
                        Matcher matcher = SEND_PATTERN.matcher(inputLine);
                        if (matcher.find()) {
                            String talker = matcher.group("talker");
                            String message = matcher.group("message");

                            if (rendezvousPoint.isActive(talker)) {
                                rendezvousPoint.getOutStream(talker).println(message);
                            } else {
                                out.println("talker " + talker + " is not active");
                            }
                        }
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e1) {

                }
            }
        }
    }
}



