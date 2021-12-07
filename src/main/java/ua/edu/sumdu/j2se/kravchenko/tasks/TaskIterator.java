package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TaskIterator implements Iterator<Task>{
    private AbstractTaskList newList;
    private int size;
    private int current = 0;

    public TaskIterator(AbstractTaskList newList) {
        this.newList = newList;
        size = newList.size();
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public Task next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return newList.getTask(current++);
    }

    @Override
    public void remove() {
        if (current < 1){
            throw new IllegalStateException();
        }
        current--;
        newList.remove(newList.getTask(current));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskIterator that = (TaskIterator) o;
        return size == that.size && current == that.current && newList.equals(that.newList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newList, size, current);
    }

    @Override
    public String toString() {
        return "TaskIterator{" +
                "size=" + size +
                ", current=" + current +
                ", newList=" + newList +
                '}';
    }
}
