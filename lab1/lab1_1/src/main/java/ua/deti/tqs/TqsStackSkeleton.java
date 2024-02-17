package ua.deti.tqs;

import java.util.LinkedList;
public class TqsStackSkeleton<T> {
    private LinkedList<T> stack;
    private int limit;

    public TqsStackSkeleton(){
        this.stack = new LinkedList<T>();
    }

    public TqsStackSkeleton(int limit) {
        this.stack = new LinkedList<T>();
        this.limit = limit;
    }

    public int getLimit(){}

    public void push(T x){}

    public T pop(){}

    public int size() {}

    public T peek() {}

    public boolean isEmpty() {}

    public boolean cleanStack() {
        return this.stack.removeAll(this.stack);
    }
}
