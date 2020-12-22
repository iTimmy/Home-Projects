package com.blog.blog.memory;

import java.util.List;
import com.blog.blog.models.*;

public interface RoleDao {
    Role getRoleByID(int id);
    Role getRoleByRole(String role);
    List<Role> getAllRoles();
    void deleteRole(int id);
    void updateRole(Role role);
    Role createRole(Role role);
}
