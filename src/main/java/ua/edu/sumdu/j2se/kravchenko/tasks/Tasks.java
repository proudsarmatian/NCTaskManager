package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        AbstractTaskList newList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        if (newList == null) {
            throw new IllegalStateException();
        }
        for (Task i : tasks) {
            if (i.nextTimeAfter(start) != null && i.nextTimeAfter(start).compareTo(end) <= 0) {
                newList.add(i);
            }
        }
        return newList;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Iterable<Task> taskIterator = incoming(tasks, start, end);

        for (Task ti : taskIterator) {
            for (LocalDateTime i = ti.nextTimeAfter(start);
                 i != null && (i.isBefore(end) || i.isEqual(end));
                 i = ti.nextTimeAfter(i)) {

                if (calendar.containsKey(i)) {
                    calendar.get(i).add(ti);
                } else {
                    Set<Task> taskSet = new HashSet<>();
                    taskSet.add(ti);
                    calendar.put(i, taskSet);
                }
            }
        }
        return calendar;
    }
}