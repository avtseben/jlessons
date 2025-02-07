package socket.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 9000);
        System.out.println("-- Connected to the server");

        // Каналы взаимодействия
        // от сервера
        var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // к серверу
        var out = new PrintWriter(clientSocket.getOutputStream(), true);

        String messageToTheServer = "Hello server";
        System.out.println(">> sending message " + messageToTheServer);
        out.println(messageToTheServer);

        System.out.println("-- Waiting for response");
        String response = in.readLine();
        System.out.println("<< server response: " + response);
    }
}
