package com.netcracker.repository;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestRepositoryAdapter {
    @Test
    public void testSortFilterMapChain() {
        Repository<String> adapter = new Repository<>();
        fillRandom(adapter);

        Repository<Integer> newAdapter = adapter
                .sorted(SorterFactory.getSorter(), Comparator.naturalOrder())
                .filter(it -> it.contains("e")).map(it -> it.indexOf("t"));

        assertEquals(4, (int) newAdapter.getByIndex(0));
        assertEquals(0, (int) newAdapter.getByIndex(1));
        assertEquals(2, newAdapter.size());
    }

    @Test
    public void testMap() {
        Repository<String> adapter = new Repository<>();
        fill(adapter);

        Repository<Integer> mapped = adapter.map(Integer::valueOf);
        assertEquals(1, (int) mapped.getByIndex(0));
        assertEquals(2, (int) mapped.getByIndex(1));
        assertEquals(3, (int) mapped.getByIndex(2));
        assertEquals(4, (int) mapped.getByIndex(3));
    }

    @Test
    public void testFilter() {
        Repository<String> adapter = new Repository<>();
        fillRandom(adapter);

        Repository<String> filtered = adapter.filter(it -> it.contains("a"));
        assertEquals("testCase", filtered.getByIndex(0));
        assertEquals("abc", filtered.getByIndex(1));
        assertEquals("random", filtered.getByIndex(2));
        assertEquals(3, filtered.size());
    }

    @Test
    public void testBubbleSort() {
        Repository<String> adapter = new Repository<>();
        fillRandom(adapter);

        Repository<String> sorted = adapter
                .sorted(SorterFactory.getBubbleSorter(), Comparator.comparingInt(it -> it.charAt(0)));
        assertEquals("abc", sorted.getByIndex(0));
        assertEquals("qwerty", sorted.getByIndex(1));
        assertEquals("random", sorted.getByIndex(2));
        assertEquals("testCase", sorted.getByIndex(3));
    }

    @Test
    public void testQuickSort() {
        Repository<String> adapter = new Repository<>();
        fill(adapter);
        adapter.swap(0, 2);
        adapter.swap(1, 2);

        Repository<String> sorted = adapter
                .sorted(SorterFactory.getSorter(), Comparator.comparingInt(it -> it.charAt(0)));
        assertEquals("1", sorted.getByIndex(0));
        assertEquals("2", sorted.getByIndex(1));
        assertEquals("3", sorted.getByIndex(2));
        assertEquals("4", sorted.getByIndex(3));
    }

    @Test
    public void testConstructor() {
        Repository<String> adapter = new Repository<>();
        fill(adapter);

        Repository<String> anotherAdapter = new Repository<>(adapter);
        assertEquals("1", anotherAdapter.getByIndex(0));
    }



    @Test
    public void testContains() {
        Repository<String> adapter = new Repository<>();
        fillRandom(adapter);

        String s1 = "test";
        String s2 = "abc";

        assertFalse(adapter.contains(s1));
        assertTrue(adapter.contains(s2));
    }

    @Test
    public void testClone() {
        Repository<String> adapter = new Repository<>();
        fill(adapter);
        Repository<String> clone = adapter.clone();

        assertNotEquals(adapter, clone);
        assertEquals(adapter.getByIndex(0), clone.getByIndex(0));
        clone.removeAt(1);
        assertNotEquals(adapter.getByIndex(1), clone.getByIndex(1));
    }

    private void fill(Repository<String> adapter) {
        adapter.add("1");
        adapter.add("2");
        adapter.add("3");
        adapter.add("4");
    }

    private void fillRandom(Repository<String> adapter) {
        adapter.add("testCase");
        adapter.add("abc");
        adapter.add("qwerty");
        adapter.add("random");
    }
}
