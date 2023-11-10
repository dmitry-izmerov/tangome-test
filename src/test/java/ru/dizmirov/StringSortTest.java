package ru.dizmirov;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringSortTest {

    @Test
    public void testEmptyInput() {
        var alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e');
        var input = "";
        var result = StringSort.sort(input, alphabet);
        assertEquals("", result);
    }

    @Test
    public void testEmptyAlphabet() {
        var alphabet = Collections.<Character>emptyList();
        var input = "abcde";
        var result = StringSort.sort(input, alphabet);
        assertEquals("abcde", result);
    }

    @Test
    public void testEmptyInputAndAlphabet() {
        var alphabet = Collections.<Character>emptyList();
        var input = "";
        var result = StringSort.sort(input, alphabet);
        assertEquals("", result);
    }

    @Test
    public void testBasicSorting() {
        var alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e');
        var input = "abcde";
        var result = StringSort.sort(input, alphabet);
        assertEquals("abcde", result);
    }

    @Test
    public void testMixedInputWithAlphabet() {
        var alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e');
        var input = "acedb";
        var result = StringSort.sort(input, alphabet);
        assertEquals("abcde", result);
    }

    @Test
    public void testInputWithMissingAlphabetCharacters() {
        var alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e');
        var input = "ac";
        var result = StringSort.sort(input, alphabet);
        assertEquals("ac", result);
    }

    @Test
    public void testInputWithExtraCharacters() {
        var alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e');
        var input = "abfcdxet";
        var result = StringSort.sort(input, alphabet);
        assertEquals("abcdefxt", result);
    }

    @Test
    public void testRepeatedCharacters() {
        var alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e');
        var input = "cccbbbaa";
        var result = StringSort.sort(input, alphabet);
        assertEquals("aabbbccc", result);
    }
}