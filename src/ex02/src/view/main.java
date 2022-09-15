package ex02.src.view;

import ex02.src.controle.threads;
import java.util.concurrent.Semaphore;

public class main {
	static Semaphore s1;
	
	public static void main(String[] args) {
		s1 = new Semaphore(1);
		
		for (int i = 0; i < 5; i++) {
			Thread t = new threads(i, s1);
			t.start();
		}
	}
}
