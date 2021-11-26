package ua.edu.sumdu.j2se.kravchenko.tasks;

/**
 * Class Task - класс для створення задач, виконання яких повязано з певним проміжком часу.
 * Вони можуть бути як повторюваними, так і одночасними.
 *
 * @version 1.00 03 Nov 2021
 * @author Artem Kravchenko
 */

public class Task {
    /** title - поле для зберігання опису складу задачі */
    private String title;

    /** time - поле зберігання часу виконання задачі */
    private int time;

    /** isActive - поле, що запамятовує активна задача чи ні */
    private boolean isActive;

    /** isRepeated - поле, що запамятовує повторювана задача чи ні */
    private boolean isRepeated;

    /** start - поле зберігання часу початка виконання повторюваної задачі */
    private int start;

    /** end - поле зберігання часу закінчення виконання повторюваної задачі */
    private int end;

    /** interval - поле зберігання інтервалу виконання повторюваної задачі */
    private int interval;

    /** Конструктор для створення одночасної задачі
     * @String title - параметр для опису задачі
     * @int time - параметр для часу виконання задачі
     * */
    public Task(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Time must be greater than zero");
        }
        this.title = title;
        this.time = time;
    }

    /** Конструктор для створення повторюваної задачі
     * @String title - параметр для опису задачі
     * @int start - параметр для часу початку виконання задачі
     * @int end - параметр для часу закінчення виконання задачі
     * @int interval - параметр для часу інтервалу виконання задачі
     * */
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        if (start < 0 || end < 0 || interval < 0) {
            throw new IllegalArgumentException("Time must be greater than zero");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isRepeated = true;
    }

    /** Гетер для поля title */
    public String getTitle() {
        return this.title;
    }

    /** Сетер для поля title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Гетер для поля isActive */
    public boolean isActive() {
        return this.isActive;
    }

    /** Сетер для поля isActive */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /** Гетер для поля time, у випадку якщо задача повторюється, повертається час початку */
    public int getTime(){
        if(isRepeated){
            return start;
        }
        else {
            return this.time;
        }
    }

    /** Сетер для поля time, якщо задача була повторюваною, то вона перестає бути повторюваною */
    public void setTime(int time) {
        this.isRepeated = false;
        this.time = time;
    }

    /** Гетер для поля start, у випадку якщо задача повторюється, повертається час початку */
    public int getStartTime() {
        if(isRepeated) {
            return this.start;
        }
        else {
            return this.time;
        }
    }

    /** Гетер для поля end, у випадку якщо задача повторюється, повертається час закінчення */
    public int getEndTime() {
        if(isRepeated) {
            return this.end;
        }
        else {
            return this.time;
        }
    }

    /** Гетер для поля interval, у випадку якщо задача повторюється, повертається інтервал, якщо ні - 0*/
    public int getRepeatInterval() {
        if(isRepeated) {
            return this.interval;
        }
        else {
            return 0;
        }
    }

    /** Сетер для модифікації часу повторюваною задачі
     * @int start - параметр для передачі часу початку задачі
     * @int end - параметр для передачі часу закінчення задачі
     * @int interval - параметр для передачі інтервалу повторювання задачі
     * */
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isRepeated = true;
    }

    /** Геттер для поля isRepeated */
    public boolean isRepeated() {
        return isRepeated;
    }

    /** Метод для визначення часу наступного виконання задачі після вказаного часу
     * @int current - параметр для передачі поточного часу
     * */
    public int nextTimeAfter(int current) {
        if(isActive()) {
            if(isRepeated) {
                if (this.start > current) {
                    return this.start;
                }
                else if(current > this.end) {
                    return -1;
                }
                else {
                    int next = this.start;

                    while (next <= current) {
                        next += this.interval;
                    }
                    if (next >= this.end) {
                        return -1;
                    }
                    return next;
                }
            }
            else {
                if (current < this.time) {
                    return this.time;
                }
                else {
                    return -1;
                }
            }
        }
        else {
            return -1;
        }
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
