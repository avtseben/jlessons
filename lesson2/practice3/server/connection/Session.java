package lesson2.practice3.server.connection;

public interface Session extends Channel{
    void setLoggedIn(String talkerOwner);
    String getTalkerOwner();
}
