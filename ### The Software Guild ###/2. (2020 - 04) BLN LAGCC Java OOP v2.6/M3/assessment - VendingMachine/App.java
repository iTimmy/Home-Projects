import controller.*;
import view.*;
import service.*;

public class App {
    public static void main (String[] args) throws Exception {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineService myService = new VendingMachineServiceImpl();
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
}