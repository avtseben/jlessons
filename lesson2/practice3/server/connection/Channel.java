package lesson2.practice3.server.connection;

public interface Channel {
    void sendToOwner(String fromTalker, String message);
}
