package ex02.src.controle;

import java.util.concurrent.Semaphore;

public class threads extends Thread {
	
	int num;
	Semaphore s1;
	
	public threads(int num, Semaphore s1) {
		this.num = num;
		this.s1 = s1;
	}
	
	@Override
	public void run() {
		if (num % 2 == 0) {
			cozinhado((int) (Math.random() * 6) + 7);
		} else {
			cozinhado((int) (Math.random() * 5) + 4);
		}
		entregaPrato();
	}
	
	private void entregaPrato() {
		try {
			s1.acquire();
			System.out.println("Num. Pedido: " + getId() + " Ser� entregue.");
			sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("Num. Pedido: " + getId() + " est� pronto.");
			s1.release();
		}
		
	}

	public void cozinhado(int d) {
		
		int tempoTotal = (d * 100);
		int estadoAtual = 0;
		
		try {
			sleep(tempoTotal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(estadoAtual < tempoTotal) {
			System.out.println("Num. Pedido: " + getId() + " faltam: " + (tempoTotal - estadoAtual) + " para ficar pronto");
			estadoAtual += 100;
		}
		System.out.println("Num. Pedido: " + getId() + " esta pronto");
	}
}
