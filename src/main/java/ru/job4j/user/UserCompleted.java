package ru.job4j.user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class UserCompleted {
    public static void main(String[] args) {
        HashMap<User, Object> map = new HashMap<>();
         User oneUser = new User("Oleg", 2,
                new GregorianCalendar(2000, Calendar.MARCH, 21));
        User twoUser = new User("Oleg", 2,
                new GregorianCalendar(2000, Calendar.MARCH, 21));
        map.put(oneUser, new Object());
        map.put(twoUser, new Object());
        System.out.println("Хэш код oneUser " + oneUser.hashCode());
        System.out.println("Хэш код twoUser " + twoUser.hashCode());
        System.out.println("Равны ли объекты по методу eqals : " + oneUser.equals(twoUser));
        System.out.println(map);
        System.out.println(map.size());
    }
}
