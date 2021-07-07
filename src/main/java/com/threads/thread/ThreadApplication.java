package com.threads.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ThreadApplication {

	@Value("${number.threads}")
	private int numberThread;

	public static void main(String[] args) {
		SpringApplication.run(ThreadApplication.class, args);
	}

	//@EventListener(ApplicationReadyEvent.class)
	@PostConstruct
	public void doSomethingAfterStartup(){
		Executa executa = new Executa();
		executa.exec(numberThread);
	}
}

class Executa {

	public void exec(int numberThread){

		for (int i = 0; i < numberThread; i++) {
			new Thread(new Escrita()).start();
		}
	}

}

class Escrita implements Runnable {

	private int i;
	private static int cont = 0;
	private int identificacao;

	public void run() {
		while(true)
			System.out.println("Número (" + identificacao + "): " + i++);
	}
	public Escrita() {
		cont++;
		identificacao = cont;
	}
}