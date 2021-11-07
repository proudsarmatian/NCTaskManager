package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.NoSuchElementException;

/**
 * Class ArrayTaskList - класс для зберігання массиву задач.
 *
 * @version 1.00 07 Nov 2021
 * @author Artem Kravchenko
 */

public class ArrayTaskList {
    /** size - приватне поле для збереження дійсного розміру масиву */
    private int size;

    /** taskList - поле типу Task для створення тілу масиву початковим розміром size */
    private Task[] taskList = new Task[size];

    /** capacity - приватне поле для зберігання інформації про наповненість масиву
     * Додав лише для майбутнього розширення класу у випадку, якщо будемо працювати з коефіцієнтом збільшення масиву. */
    private int capacity;

    /** Публічний метод для додавання задачі в кінець масиву.
     * Якщо наповненість масиву рівна його розміру, то масив розширяється і переприсвоюється посилання на новий.
     * @Task task - параметр для передачі задачі
     * */
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
    public int size() {
        return this.size;
    }

    /** Публічний метод для повертання задачі з масиву.
     * Задача повертається лише у випадку якщо вона там є.
     * @int index - параметр для індексу задачі в масиві.
     * */
    public Task getTask(int index) {
        if (index < 0 || index > this.size) {
            throw new NoSuchElementException();
        }

        return this.taskList[index];
    }

    /** Публічний метод визначення задач, що відбудуться в певному проміжку часу.
     * Метод повертає масив задач, що задовольняють інтервалу часу.
     * @int from - параметр для передачі початку інтервалу часу.
     * @int to - параметр для передачі кінця інтервалу часу.
     * */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList newList = new ArrayTaskList();

        for (int i = 0; i < this.size; ++i) {
            if (this.taskList[i].isActive() && this.taskList[i].getStartTime() > from &&
                    this.taskList[i].getEndTime() < to) {
                newList.add(this.taskList[i]);
            }
        }

        return newList;
    }
}
