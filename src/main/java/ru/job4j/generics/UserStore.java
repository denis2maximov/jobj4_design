package ru.job4j.generics;

public class UserStore implements Store<User> {

    private final Store<User> odin = new MemStore<>();

    @Override
    public void add(User model) {
        odin.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return odin.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return odin.delete(id);
    }

    @Override
    public User findById(String id) {
        return odin.findById(id);
    }
}