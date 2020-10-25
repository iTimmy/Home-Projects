package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.controller.*;
import com.mycompany.vendingmachine.controller.VendingMachineController;
import com.mycompany.vendingmachine.view.*;
import com.mycompany.vendingmachine.service.*;
import com.mycompany.vendingmachine.dao.*;

public class App {
    public static void main (String[] args) throws Exception {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDAO = new VendingMachineDaoImpl();
        VendingMachineAuditDao auditDAO = new VendingMachineAuditDaoImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDAO, auditDAO);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
    }
}