package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenCreateRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Admin"));
    }

    @Test
    public void whenCreateDuplicateRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.add(new Role("1", "Guest"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Admin"));
    }

    @Test
    public void whenChangeRoleNotExist() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("10", new Role("1", "Guest"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Admin"));
    }

    @Test
    public void whenChangeRoleThatIs() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("1", new Role("1", "Guest"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Guest"));
    }

    @Test
    public void whenDeleteRoleThatIs() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenDeleteRoleThatNotExist() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Admin"));
    }
}