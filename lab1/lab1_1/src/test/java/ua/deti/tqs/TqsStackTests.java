package ua.deti.tqs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.deti.tqs.TqsStackSkeleton;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class TqsStackTests {
    private TqsStackSkeleton<String> stack = new TqsStackSkeleton<String>() ;
    private TqsStackSkeleton<String> stack_bound = new TqsStackSkeleton<String>(7) ;

    @AfterEach
    public void cleanStack(){
        stack.cleanStack();
        stack_bound.cleanStack();
    }

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
        Object x = stack.peek();
        assertEquals(stack_size_after_push, stack.size(), "Push Then Peek Size Not The Same");
        assertEquals("mina", x, "Value Not The Same");
    }

    @DisplayName("6th")
    @Test
    void afterNPops() {
        Integer stack_size = stack.size();
        for (Integer i = 0; i <= stack_size; i++) {
            stack.pop();
        }

        assertTrue(stack.size() == 0, "After N Pops Size Not 0");
        assertTrue(stack.isEmpty(), "After N Pops Stack Is Not Empty");
    }

    @DisplayName("7th")
    @Test
    void popFromEmptyStackNoSuchElementException() {
       assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
       }, "No Such Element Exception Not Thrown When Popping From Empty Stack");
    }

    @DisplayName("8th")
    @Test
    void peekFromEmptyStackNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        }, "No Such Element Exception Not Thrown When Peeking From Empty Stack");
    }

    @DisplayName("9th")
    @Test
    void pushOntoFullStackNoSuchStateException(){
        for (Integer j = 0; j <= stack_bound.getLimit(); j++){
            stack_bound.push("louca");
        }
        assertThrows(IllegalStateException.class, () -> stack_bound.push("não"), "IllegalStateException Not Thrown When Pushing Onto Full Stack");
    }
}
