package lesson2.practice3;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RendezvousPoint {

    private List<String> activeTalkers = new ArrayList<>();
    private Map<String, PrintWriter> outputStreams = new HashMap<>();
    private Map<String, BufferedReader> inputStreams = new HashMap<>();

    public void goIn(String talker, BufferedReader in, PrintWriter out) {
        activeTalkers.add(talker);
        outputStreams.put(talker, out);
        inputStreams.put(talker, in);
    }

    public boolean isActive(String talker) {
        return activeTalkers.contains(talker);
    }

    public PrintWriter getOutStream(String talker) {
        return outputStreams.get(talker);
    }
}
