package ua.deti.tqs;

import java.util.LinkedList;
public class TqsStackSkeleton<T> {
    private LinkedList<T> stack;
    private int limit;

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
        stack.addFirst(x);
    }

    public T pop(){
        return stack.pop();
    }

    public Integer size() {
        return stack.size();
    }

    public T peek() {
        return stack.peekFirst();
    }

    public boolean isEmpty() {
        if (stack.size() == 0) {return true;}
        else {return ;}
    }

    public boolean cleanStack() {
        return this.stack.removeAll(this.stack);
    }
}
