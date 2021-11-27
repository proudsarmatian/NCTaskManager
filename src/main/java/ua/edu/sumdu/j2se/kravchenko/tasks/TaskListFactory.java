package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.NoSuchElementException;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == type.ARRAY) {
            ArrayTaskList list = new ArrayTaskList();
            return list;
        }
        else if(type == type.LINKED) {
            LinkedTaskList list = new LinkedTaskList();
            return list;
        }
        else {
            throw new NoSuchElementException();
        }
    }
}
