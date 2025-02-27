package lesson2.practice3.server.controller;

import lesson2.practice3.server.connection.Session;

public interface Controller {
    boolean isApplicable(String inputLine);
    void apply(String inputLine, Session session);
}
