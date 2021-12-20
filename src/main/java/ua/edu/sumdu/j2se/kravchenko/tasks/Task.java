package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Task - класс для створення задач, виконання яких повязано з певним проміжком часу.
 * Вони можуть бути як повторюваними, так і одночасними.
 *
 * @version 1.00 03 Nov 2021
 * @author Artem Kravchenko
 */

public class Task extends Object implements Cloneable{
    private String title;
    private LocalDateTime time;
    private boolean isActive;
    private boolean isRepeated;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;

    public Task(String title, LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("Illegal date");
        }
        this.title = title;
        this.time = time;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (start == null || end == null || !end.isAfter(start) || interval < 0) {
            throw new IllegalArgumentException("Time must be greater than zero");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isRepeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public LocalDateTime getTime(){
        if(isRepeated){
            return start;
        }
        else {
            return time;
        }
    }

    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        if(isRepeated){
            this.isRepeated = false;
        }
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if(isRepeated) {
            return start;
        }
        else {
            return time;
        }
    }

    public LocalDateTime getEndTime() {
        if(isRepeated) {
            return end;
        }
        else {
            return time;
        }
    }
    public int getRepeatInterval() {
        if(isRepeated) {
            return interval;
        }
        else {
            return 0;
        }
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null || !end.isAfter(start) || interval <= 0) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isRepeated = true;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (isActive()) {
            if (!isRepeated()) {
                if (time.isAfter(current)) {
                    return time;
                } else return null;
            }
            for (LocalDateTime i = start; i.isBefore(end) || i.equals(end); i = i.plusSeconds(interval)) {
                if (current.isBefore(i)) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time && isActive == task.isActive && isRepeated == task.isRepeated &&
                start == task.start && end == task.end && interval == task.interval && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, isActive, isRepeated, start, end, interval);
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task newTask = (Task) super.clone();
        newTask.title = (this.title);
        newTask.time = this.time;
        newTask.isActive = this.isActive;
        newTask.isRepeated = this.isRepeated;
        newTask.start = this.start;
        newTask.end = this.end;
        newTask.interval = this.interval;
        return newTask;
    }

    /** Метод для виводу інформації про обєкт у вигляді строки */
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", isActive=" + isActive +
                ", isRepeated=" + isRepeated +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                '}';
    }
}
