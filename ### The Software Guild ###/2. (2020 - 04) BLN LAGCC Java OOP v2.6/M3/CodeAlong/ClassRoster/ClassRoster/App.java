
import ui.*;
import controller.*;

public class App {
    public static void main(String[] args) {
        ClassRosterController controller = new ClassRosterController();
        controller.run();

        /*
        UserIOConsoleImpl userio = new UserIOConsoleImpl();
        ClassRosterView crv = new ClassRosterView();
        userio.print("hi");
        crv.Display();
        userio.readInt("ok");
        */
    }   
}