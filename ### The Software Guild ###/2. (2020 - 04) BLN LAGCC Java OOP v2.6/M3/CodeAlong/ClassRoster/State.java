import java.util.*;

public class State {
    Map<String, String> states = new HashMap<>();
    public static void main (String[] args) {
        State state = new State();
        state.states();
        state.capitals();
    }

    public void states() {
        states.put("Alabama", "Montgomery");
        states.put("Alaska", "Juneau");
        states.put("Arizona", "Pheonix");
        states.put("Arkansas", "Little Rock");

        Collection<String> stateNames = states.keySet();
        Collection<String> stateNamess = states.values();

        System.out.println("\n\n\nSTATES:\n=======");
        for (String stateRows: stateNames) {
            System.out.println(stateRows);
        }

        System.out.println("\nCAPITALS:\n=========");
        for (String stateRowss: stateNamess) {
            System.out.println(stateRowss);
        }

        System.out.println("\n\n\nSTATE/CAPITAL PAIRS:\n====================");
        for (String pairNames: stateNamess) {
            System.out.println(states.keySet() + " - " + states.values());
        }
    }
    public void capitals() {

    }
}