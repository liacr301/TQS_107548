package ua.deti.tqs;

import java.util.LinkedList;
public class TqsStackSkeleton<T> {
    private LinkedList<T> stack;

    public TqsStackSkeleton(){
        this.stack = new LinkedList<T>();
    }
    public void push(T x){}

    public T pop(){}

    public int size() {}

    public T peek() {}

    public boolean isEmpty() {}
}
