package com.example.example.memory;

import java.util.List;
import com.example.example.models.*;

public interface RoleDao {
    Role getRoleById(int id);
    Role getRoleByRole(String role);
    List<Role> getAllRoles();
    void deleteRole(int id);
    void updateRole(Role role);
    Role createRole(Role role);
}
