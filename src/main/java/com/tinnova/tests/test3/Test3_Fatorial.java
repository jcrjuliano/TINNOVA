package com.tinnova.tests.test3;


public class Test3_Fatorial {

	public static Integer fatorial(Integer numero) {
		if(numero >= 1) {
			return numero * fatorial(numero -1);
		} else {
			return 1;
		}				
	}
	
	public static void main(String[] args) {
		
		System.out.println("Fatorail de 5 é: " + fatorial(5));
		System.out.println("Fatorail de 0 é: " + fatorial(0));
		System.out.println("Fatorail de 6 é: " + fatorial(6));
	}
}
