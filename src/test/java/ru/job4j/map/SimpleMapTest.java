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
        SimpleMap map = new SimpleMap();
        assertTrue(map.put(9, "Obormot 14"));
    }

    @Test
    public void whenPutNotOk() {
        SimpleMap map = new SimpleMap();
        map.put(9, "Obormot 14");
        assertFalse(map.put(9, "Obormot 14"));
    }

    @Test
    public void whenGetNotOk() {
        SimpleMap map = new SimpleMap();
        map.put(9, "Obormot 14");
        assertFalse(map.put(9, "Obormot 14"));
    }
    @Test
    public void whenGetVolume() {
        SimpleMap map = new SimpleMap();
        map.put(9, "Obormot 14");
        assertThat(map.get(9), is("Obormot 14"));
    }

    @Test
    public void whenRemoveOk() {
        SimpleMap map = new SimpleMap();
        map.put(9, "Obormot 14");
        assertTrue(map.remove(9));
    }

    @Test
    public void whenRemoveNotOk() {
        SimpleMap map = new SimpleMap();
        assertFalse(map.remove(9));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentNo() {
        SimpleMap map = new SimpleMap();
        Iterator it = map.iterator();
        while (it.hasNext()) {
            map.put(2, 8457);
        }
    }

    @Test
    public void whenAddIterHasNextTrue() {
        SimpleMap map = new SimpleMap();
        map.put(2, "klooo");
        Iterator<Integer> it = map.iterator();
        Assert.assertThat(it.hasNext(), Is.is(true));
    }
}
