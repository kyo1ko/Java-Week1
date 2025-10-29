package Week7;
import java.util.*;
public class StackQueueExample {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println("Stack pop: " + stack.pop()); // C

        Queue<String> queue = new LinkedList<>();
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        System.out.println("Queue poll: " + queue.poll()); // A
    }
}
