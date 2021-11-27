package ua.edu.sumdu.j2se.kravchenko.tasks;

public abstract class AbstractTaskList {
    protected int size;

    public abstract int size();
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList newList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        for (int i = 0; i < this.size; ++i) {
            if (this.getTask(i).isActive() && this.getTask(i).getStartTime() > from &&
                    this.getTask(i).getEndTime() < to) {
                newList.add(this.getTask(i));
            }
        }

        return newList;
    };
}
