package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.Arrays;

/**
 * Class ArrayTaskList - класс для зберігання массиву задач.
 *
 * @version 1.00 07 Nov 2021
 * @author Artem Kravchenko
 */

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    /** taskList - поле типу Task для створення тілу масиву початковим розміром size */
    private Task[] taskList = new Task[size];

    /** capacity - приватне поле для зберігання інформації про наповненість масиву
     * Додав лише для майбутнього розширення класу у випадку, якщо будемо працювати з коефіцієнтом збільшення масиву. */
    private int capacity;

    /** Публічний метод для додавання задачі в кінець масиву.
     * Якщо наповненість масиву рівна його розміру, то масив розширяється і переприсвоюється посилання на новий.
     * @Task task - параметр для передачі задачі
     * */
    @Override
    public void add(Task task) {
        if (this.capacity == this.size) {
            this.size += 1;

            Task [] newList = new Task[this.size];
            System.arraycopy(this.taskList, 0, newList, 0, this.taskList.length);
            this.taskList = newList;
        }

        this.taskList[this.capacity] = task;
        ++this.capacity;
    }

    /** Публічний метод для видалення задачі з масиву.
     * Повертається істина у випадку якщо така задача була в масиві.
     * @Task task - параметр для передачі задачі
     * */
    @Override
    public boolean remove(Task task) {
        for (int i = 0; i < this.size; ++i) {
            if (this.taskList[i].equals(task)) {
                Task [] newList = new Task[this.size - 1];

                for (int j = 0, k = 0; j < this.size; ++j) {
                    if (j != i) {
                        newList[k++] = this.taskList[j];
                    }
                }

                this.taskList = newList;
                this.size -= 1;
                this.capacity -= 1;
                return true;
            }
        }
        return false;
    }

    /** Публічний метод для визначення розміру масиву. */
    @Override
    public int size() {
        return this.size;
    }

    /** Публічний метод для повертання задачі з масиву.
     * Задача повертається лише у випадку якщо вона там є.
     * @int index - параметр для індексу задачі в масиві.
     * */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        return this.taskList[index];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "size=" + size +
                ", taskList=" + Arrays.toString(taskList) +
                ", capacity=" + capacity +
                '}';
    }
}
