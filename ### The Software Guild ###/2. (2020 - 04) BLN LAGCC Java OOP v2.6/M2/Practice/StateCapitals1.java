import java.util.*;

public class StateCapitals1 {
    public static void main (String[] args) {
        Map<String, String> states = new HashMap<>();

        states.put("Alabama", "Montgomery");
        states.put("Alaska", "Juneau");
        states.put("Arizona", "Pheonix");
        states.put("Arkansas", "Little Rock");

        Set<String> stateNames = states.keySet();
        Collection<String> capitalNames = states.values();

        System.out.println("\nSTATES:\n=======");
        for (String current : stateNames) {
            System.out.println(current);
        }

        System.out.println("\nCAPITALS:\n=======");
        for (String current : capitalNames) {
            System.out.println(current);
        }

        System.out.println("\nSTATE/CAPITALS:\n=======");
        System.out.println(states);
    }
}