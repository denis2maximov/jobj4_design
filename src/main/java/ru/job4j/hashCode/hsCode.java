package ru.job4j.hashCode;

public class hsCode {
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        Object h = new Object();
        int i;
        System.out.println(i = hash(h.hashCode()));
        String string = "Б***, какой *******, а не тема!";

        System.out.println("Это хэш от строки " + hash(string));
        System.out.println("Это хэш-код  от строки " + string.hashCode());
        int i2 = string.hashCode();
        String i3 = binary(i2);
        System.out.println(i3);
        System.out.println(Integer.toBinaryString(i2));
        System.out.println(Integer.toBinaryString(~i2));
        int outBynary = 123;
        int moveBynary = 4;
        String outBynary2 = binary(outBynary);
        System.out.println("Это бинарное представление числа "
                + outBynary + " : " + binary(outBynary));
        System.out.println("Выполняем на этим числом "
                + outBynary + " бинарный сдвиг вправо на 4 : " + binary(123 >> 4));
   }

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

}
