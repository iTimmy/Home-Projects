package dao;

import java.util.*;
import dto.*;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public VendingMachine onlyItem;

    public VendingMachineDaoStubImpl() {
        onlyItem = new VendingMachine("0001", 1);
        onlyItem.setItemName("Ada");
        onlyItem.setItemCost(1.0);
        onlyItem.setItemQuantity(1);
    }

    public VendingMachineDaoStubImpl(VendingMachine testVendingMachine) {
        this.onlyItem = testVendingMachine;
    }

    @Override
    public List<VendingMachine> getAllItems() {
        List<VendingMachine> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public VendingMachine getItem(String itemName, int itemQuantity, double userMoney) throws Exception {
        if (itemName.equals(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void removeItem(String itemName, int itemQuantity) {
        if (itemName.equals(onlyItem.getItemName())) {
            //return onlyItem;
        } else {
           // return null;
        }
    }

    @Test
    public void testGetAllStudents() throws Exception {
        // ARRANGE
        VendingMachine testClone = new VendingMachine("0001", 1);
        testClone.setItemName("Ada");
        testClone.setItemCost(1.0);
        testClone.setItemQuantity(1);

        // ACT & ASSERT
        assertEquals(1, service.getAllStudents().size(), "Should only have one student.");
        assertTrue(service.getAllStudents().contains(testClone), "The one student should be Ada.");
    }

    @Test
    public void testGetStudent() throws Exception {
        // ARRANGE
        VendingMachine testClone = new VendingMachine("0001", 1.0);
        testClone.setItemName("Ada");
        testClone.setItemCost(1.0);
        testClone.setItemQuantity(1);

        // ACT & ASSERT
        VendingMachine shouldBeAda = service.getStudent("0001");
        assertNotNull(shouldBeAda, "Getting 0001 should be not null.");
        assertEquals(testClone, shouldBeAda, "Student stored under 0001 should be Ada.");

        VendingMachine shouldBeNull = service.getStudent("0042");
        assertNull(shouldBeNull, "Getting 0042 should be null.");

    }

    @Test
    public void testRemoveStudent() throws Exception {
        // ARRANGE
        VendingMachine testClone = new VendingMachine("0001", 1.0);
        testClone.setItemName("Ada");
        testClone.setItemCost(1.0);
        testClone.setItemQuantity(1);

        // ACT & ASSERT
        VendingMachine shouldBeAda = service.removeStudent("0001");
        assertNotNull(shouldBeAda, "Removing 0001 should be not null.");
        assertEquals(testClone, shouldBeAda, "Student removed from 0001 should be Ada.");

        VendingMachine shouldBeNull = service.removeStudent("0042");
        assertNull(shouldBeNull, "Removing 0042 should be null.");

    }
}