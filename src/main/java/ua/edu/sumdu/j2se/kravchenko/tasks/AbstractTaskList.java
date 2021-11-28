package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.NoSuchElementException;

public abstract class AbstractTaskList {
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
}
