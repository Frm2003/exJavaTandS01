package ex01.src.view;

import java.util.concurrent.Semaphore;
import ex01.src.controller.controle;

public class main {
	
	public static Semaphore s1;
	
	public static void main(String[] args) {
		
		s1 = new Semaphore(1);
		
		for (int i = 0; i < 21; i++) {
			Thread t = new controle(s1);
			t.start();
		}
	}
}
