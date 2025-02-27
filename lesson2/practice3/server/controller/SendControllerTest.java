package lesson2.practice3.server.controller;

import lesson2.practice3.server.connection.Channel;
import lesson2.practice3.server.connection.Session;
import lesson2.practice3.server.RendezvousPoint;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SendControllerTest {

    @Test
    void testParse() {
        RendezvousPoint rendezvousPoint = mock(RendezvousPoint.class);
        Session session = mock(Session.class);
        Channel joesChannel = mock(Channel.class);

        SendController uut = new SendController(rendezvousPoint);
        when(rendezvousPoint.getOutput("Joe")).thenReturn(joesChannel);
        uut.apply("/send Joe Hello my friend!", session);
    }
}