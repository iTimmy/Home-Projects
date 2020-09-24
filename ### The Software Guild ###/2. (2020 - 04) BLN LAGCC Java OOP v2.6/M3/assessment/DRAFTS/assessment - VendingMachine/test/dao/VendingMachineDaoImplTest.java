package test.dao;

import java.io.*;
import org.unit.Test;

public class VendingMachineDaoImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }

}