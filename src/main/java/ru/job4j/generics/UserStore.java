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

    public static void main(String[] args) {
        UserStore odin = new UserStore();
        odin.add(new User("1", "Petr"));
        odin.replace("10", new User("10", "Maxim"));
        User result = odin.findById("1");
        System.out.println(result);
    //    String string = result.getUsername();
        System.out.println(" имя " + result.getUsername());
    }
}