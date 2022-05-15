//package ru.job4j.map;
//
//import org.junit.Test;
//import ru.job4j.generics.Role;
//import ru.job4j.generics.RoleStore;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
//public class SimpleMapTest {
//
//    @Test
//    public void whenPutOk() {
//        SimpleMap map = new SimpleMap();
//        map.put(9, "Obormot 14");
//        assertThat(map.get(9), is("Obormot 14"));
//        }
//
//    @Test
//    public void whenPutNotOk() {
//        SimpleMap map = new SimpleMap();
//        map.put(9, "Obormot 14");
//        assertFalse(map.put(9, "Obormot 14"));
//    }
//
//    @Test
//    public void whenGetNotOk() {
//        SimpleMap map = new SimpleMap();
//        map.put(9, "Obormot 14");
//        assertNull(map.get(10));
//    }
//    @Test
//    public void whenGetVolume() {
//        SimpleMap map = new SimpleMap();
//        map.put(9, "Obormot 14");
//        assertThat(map.get(9), is("Obormot 14"));
//    }
//
//    @Test
//    public void whenRemoveOk() {
//        SimpleMap map = new SimpleMap();
//        map.put(9, "Obormot 14");
//        assertTrue(map.remove(9));
//    }
//
//    @Test
//    public void whenRemoveNotOk() {
//        SimpleMap map = new SimpleMap();
//        assertFalse(map.remove(9));
//    }
//
//    @Test
//    public void iterator() {
//    }
//}
