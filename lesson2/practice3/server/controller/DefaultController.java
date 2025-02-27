package lesson2.practice3.server.controller;


import lesson2.practice3.server.connection.Session;

import static lesson2.practice3.server.Constants.SYSTEM_TALKER;


class DefaultController implements Controller {

    public boolean isApplicable(String inputLine) {
        return true;
    }

    public void apply(String inputLine, Session session) {
        session.sendToOwner(SYSTEM_TALKER, "Bad command");
    }
}
