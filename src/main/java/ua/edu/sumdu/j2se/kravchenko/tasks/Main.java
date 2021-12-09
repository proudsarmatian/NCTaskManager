package ua.edu.sumdu.j2se.kravchenko.tasks;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Task task = new Task("Title", 120);
		Task A = new Task("Title1", 20);
		Task B = new Task("Title2", 60);
		LinkedTaskList list = new LinkedTaskList();
		list.add(task);
		list.add(A);
		list.add(B);
		System.out.println(list.size());
		list.remove(A);
		System.out.println(list.size());
		System.out.println(A.getTitle());;
		list.remove(B);
		list.remove(task);
		System.out.println(list.size());
	}

}
