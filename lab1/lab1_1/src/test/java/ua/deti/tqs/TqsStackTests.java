package ua.deti.tqs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.deti.tqs.TqsStackSkeleton;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class TqsStackTests {
    private TqsStackSkeleton<String> stack = new TqsStackSkeleton<String>() ;

    @DisplayName("1st")
    @Test
    void isEmptyOnConstruction() {
        assertTrue(stack.isEmpty(), "Not Empty");
    }

    @DisplayName("2nd")
    @Test
    void size0OnConstruction() {
        assertEquals(0,stack.isEmpty(), "Size Not 0");
    }

    @DisplayName("3rd")
    @Test
    void afterNPushes() {
        stack.push("eu");
        stack.push("sou");
        stack.push("a");
        stack.push("arminda");

        assertEquals(4, stack.size(), "After N Pushes Size Not N");
        assertFalse(stack.isEmpty(), "Stack Empty After N Pushes");
    }

    @DisplayName("4th")
    @Test
    void pushThenPop() {
        stack.push("ana");
        assertEquals("ana",stack.pop(), "Push Then Pop Value Not The Same");
    }

    @DisplayName("5th")
    @Test
    void pushThenPeek() {
        stack.push("mina");
        int stack_size_after_push = stack.size();
        stack.peek();
        assertEquals(stack_size_after_push, stack.size(), "Push Then Peek Size Not The Same");
    }
}
