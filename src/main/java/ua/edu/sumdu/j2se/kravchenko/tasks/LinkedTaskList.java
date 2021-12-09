package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.NoSuchElementException;
import java.util.stream.Stream;


public class LinkedTaskList  extends AbstractTaskList implements Cloneable {
    private class Node {
        Task task;
        Node prev;
        Node next;

        public Node(Task task) {
            this(task,null,null);
        }

        public Node(Task task, Node next, Node prev) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Stream<Task> getStream() {
        return super.getStream();
    }

    @Override
    public ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }


    @Override
    public void add(Task task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = tail = newNode;
            head.prev = null;
            tail.next = null;
        }
        else {
            Node current = head;

            while (current.next != null && !current.task.equals(newNode.task)) {
                current = current.next;
            }

            if (current.next == null) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            else if (current.prev == null) {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
            }
            else {
                newNode.next = current;
                current.prev.next = newNode;
                newNode.prev = current.prev;
                current.prev = newNode;
            }
        }
        size++;
    }

    @Override
    public boolean remove(Task task) {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.task.equals(task) && head.next != null) {
            head.next.prev = null;
            head = head.next;
            --size;
            return true;
        }
        else if (head.task.equals(task) && head.next == null) {
            head = null;
            --size;
            return true;
        }
        else if (tail.task.equals(task)) {
            tail.prev.next = null;
            tail = tail.prev;
            --size;
            return true;
        }
        else {
            Node current = head;
            while (current.next != null && !current.task.equals(task)) {
                current = current.next;
            }
            if (!current.task.equals(task)) {
                throw new NoSuchElementException();
            }
            if (current.next != null) {
                current.next.prev = current.prev;
                current.prev.next = current.next;
            }

            --size;
            return true;
        }
    }

    @Override
    public Task getTask(int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                return head.task;
            }
            else if (index == size) {
                return tail.task;
            }
            else {

                Node current = head;
                for (int i = 0; i < index && current.next != null; ++i) {
                    current = current.next;
                }

                return current.task;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
