package lesson2.practice3.server.controller;

import lesson2.practice3.server.connection.Session;
import lesson2.practice3.server.RendezvousPoint;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoginControllerTest {

    @Test
    void parseTest() {
        RendezvousPoint rendezvousPoint = mock(RendezvousPoint.class);
        Session session = mock(Session.class);
        LoginController uut = new LoginController(rendezvousPoint);

        uut.apply("/login Joe", session);
        verify(rendezvousPoint).goIn("Joe", session);
        verify(session).setLoggedIn("Joe");
    }
}