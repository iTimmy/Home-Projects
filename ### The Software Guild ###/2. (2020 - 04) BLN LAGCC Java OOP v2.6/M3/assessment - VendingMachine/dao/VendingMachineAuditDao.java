package dao;

public interface VendingMachineAuditDao {
    void writeAuditEntry(String entry) throws Exception;
}