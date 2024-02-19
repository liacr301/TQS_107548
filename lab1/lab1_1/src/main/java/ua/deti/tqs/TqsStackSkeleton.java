package ua.deti.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStackSkeleton<T> {
    private LinkedList<T> stack;
    private Integer limit;

    public TqsStackSkeleton(){
        this.stack = new LinkedList<T>();
    }

    public TqsStackSkeleton(Integer limit) {
        this.stack = new LinkedList<T>();
        this.limit = limit;
    }

    public Integer getLimit(){
        return limit;
    }

    public void push(T x){
        if (limit != null && stack.size() >= limit.intValue()){
            throw new IllegalStateException();
        }
        stack.addFirst(x);
    }

    public T pop(){
        if (stack.isEmpty()){
            throw new NoSuchElementException();
        }
        return stack.remove();
    }

    public Integer size() {
        return stack.size();
    }

    public T peek() {
        if (stack.isEmpty()){
            throw new NoSuchElementException();
        }
        return stack.peekFirst();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean cleanStack() {
        return this.stack.removeAll(this.stack);
    }
}
