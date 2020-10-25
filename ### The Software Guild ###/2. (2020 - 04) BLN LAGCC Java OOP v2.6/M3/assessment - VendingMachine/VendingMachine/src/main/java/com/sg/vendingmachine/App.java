package com.sg.vendingmachine;

import com.sg.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.view.UserIOConsoleImpl;
import com.sg.vendingmachine.view.UserIO;
import com.sg.vendingmachine.view.VendingMachineView;
import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import java.io.IOException;

public class App {
    public static void main (String[] args) throws IOException, VendingMachineInsufficientFundsException {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDAO = new VendingMachineDaoImpl();
        VendingMachineAuditDao auditDAO = new VendingMachineAuditDaoImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDAO, auditDAO);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
    }
}