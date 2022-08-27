package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleDao {

    public List<Role> listRoles();

    public void addRole(Role role);

    public Role getRoleByName(String name);

//    public Role getRoleById(long id);

    Collection<Role> findByIdRoles(List<Long>roles);


}
