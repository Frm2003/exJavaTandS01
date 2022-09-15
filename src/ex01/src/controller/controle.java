package ex01.src.controller;

import java.util.concurrent.Semaphore;

public class controle extends Thread {
	
	Semaphore s1;
	
	public controle(Semaphore s1) {
		this.s1= s1;
	}

	@Override
	public void run() {
		if (getId() % 3 == 0) {
			
			calculos((int) (Math.random() * 9) + 2, 1);
			transacaoBd(1000, 1);
			calculos((int) (Math.random() * 9) + 2, 1);
			transacaoBd(1000, 1);
			
		} else if (getId() % 3 == 1) {
			
			calculos((int) (Math.random() * 2) + 1, 2);
			transacaoBd(1500, 2);
			calculos((int) (Math.random() * 2) + 1, 2);
			transacaoBd(1500, 2);
			calculos((int) (Math.random() * 2) + 1, 2);
			transacaoBd(1500, 2);
			
		} else if (getId() % 3 == 2) {
			
			calculos((int) (Math.random() * 10) + 5, 3);
			transacaoBd(1500, 3);
			calculos((int) (Math.random() * 10) + 5, 3);
			transacaoBd(1500, 3);
			calculos((int) (Math.random() * 10) + 5, 3);
			transacaoBd(1500, 3);
		}
		System.out.println("Finalizou todas as opera��es ");
	}

	public void calculos(int d, int tipo) {
		System.out.println("ID:" + getId() + " | caso: " + tipo + " | Demorara" + d + "s | esta realizando seus calculos");
		try {
			sleep(d * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ID:" + getId() + " | caso: " + tipo + " | Acabou de realizar seu calculos");
	}
	
	public void transacaoBd(int d, int tipo) {
		try {
			s1.acquire();
			System.out.println("ID:" + getId() + " | caso: " + tipo + " | esta em transa��o com o banco");
			try {
				sleep(d);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("ID:" + getId() + " | caso: " + tipo + " | finalizou transa��o com o banco");
			s1.release();
		}
	}

}
