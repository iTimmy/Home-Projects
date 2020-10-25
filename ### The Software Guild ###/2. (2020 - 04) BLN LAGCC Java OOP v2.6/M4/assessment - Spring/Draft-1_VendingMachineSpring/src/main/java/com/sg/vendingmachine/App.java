package com.sg.vendingmachine;

import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.view.VendingMachineView;
import com.sg.vendingmachine.view.UserIO;
import com.sg.vendingmachine.view.UserIOConsoleImpl;
import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main (String[] args) throws Exception {
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        VendingMachineController controller = appContext.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}