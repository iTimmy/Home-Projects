import controller.*;

public class App {
    public static void main (String[] args) throws Exception {
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}