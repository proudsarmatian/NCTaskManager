package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {
    protected int size;

    public abstract int size();
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);

    public abstract ListTypes.types getType();

    public Stream<Task> getStream() {
        Task[] array = new Task[this.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = getTask(i);
        }
        return Arrays.stream(array);
    }

    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList newList = TaskListFactory.createTaskList(getType());

        getStream().filter(task -> task.nextTimeAfter(from) >= from && task.nextTimeAfter(from) <= to).forEach(newList::add);
        return newList;
    }


    @Override
    public Iterator<Task> iterator() {
        return new TaskIterator(this);
    }

    @Override
    public int hashCode() {
        int hash = 1;

        for (int i = 0; i < this.size; ++i) {
            hash = hash + (this.getTask(i) == null ? 0 : this.hashCode());
        }

        return hash;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList newList = TaskListFactory.createTaskList(getType());

        for (Task t : this) {
            newList.add(t);
        }
        return newList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof AbstractTaskList)) return false;
        AbstractTaskList list = (AbstractTaskList) obj;
        if (this.size != list.size()) return false;
        else return true;
    }
}
