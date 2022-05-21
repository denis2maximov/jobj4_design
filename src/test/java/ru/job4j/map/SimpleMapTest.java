package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutOk() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(9, "Obormot 14"));
    }

    @Test
    public void whenPutNotOk() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(9, "Obormot 14");
        assertFalse(map.put(9, "Obormot 14"));
    }

    @Test
    public void whenGetNotOk() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(9, "Obormot 14");
        assertFalse(map.put(9, "Obormot 14"));
    }
    @Test
    public void whenGetVolume() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(9, "Obormot 14");
        assertThat(map.get(9), is("Obormot 14"));
    }

    @Test
    public void whenRemoveOk() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(9, "Obormot 14");
        assertTrue(map.remove(9));
    }

    @Test
    public void whenRemoveNotOk() {
        SimpleMap<Integer, String>  map = new SimpleMap<>();
        assertFalse(map.remove(9));
    }


    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentNo() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<?> it = map.iterator();
        map.put(21, "Revers home");
        while (it.hasNext()) {
            map.remove(21);
        }
    }

    @Test
    public void whenAddIterHasNextTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(2, "klooo");
        Iterator<Integer> it = map.iterator();
        Assert.assertThat(it.hasNext(), Is.is(true));
    }
}

