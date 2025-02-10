package socket.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class SingleConnectSocketServer {

    //Одноразовый сервер
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("Server is listening connections on port " + 9000);
        //открывает сокет и блокируется пока не будет подключения
        Socket clientSocket = serverSocket.accept();
        System.out.println("-- Client connected");

        // Каналы взаимодействия
        // от клиента
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // к клиенту
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("<< received message from client: " + inputLine);
            String response = "echo: " + inputLine;
            System.out.println(">> sending response " + response);
            out.println(response);
        }
    }
}



