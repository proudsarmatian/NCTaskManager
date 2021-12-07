package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.NoSuchElementException;
import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task> {
    protected int size;

    public abstract int size();
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);

    public ListTypes.types listType (AbstractTaskList list) {
        if (list instanceof ArrayTaskList) {
            return ListTypes.types.ARRAY;
        }
        else if (list instanceof LinkedTaskList) {
            return ListTypes.types.LINKED;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList newList = TaskListFactory.createTaskList(listType(this));

        for (int i = 0; i < this.size; ++i) {
            if (this.getTask(i).isActive() && this.getTask(i).getStartTime() > from &&
                    this.getTask(i).getEndTime() < to) {
                newList.add(this.getTask(i));
            }
        }

        return newList;
    };

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
        AbstractTaskList newList = TaskListFactory.createTaskList(listType(this));

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
