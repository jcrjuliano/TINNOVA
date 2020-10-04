package com.tinnova.tests.test4;

public class Test4_SomaMultiplos {
	
	public static Integer somarMultiplos(Integer entrada) {
		Integer total = 0;
		
		for(Integer i = 0; i < entrada; i++) {
			if ((i%3 == 0) || (i%5 == 0)) {
				total += i;
			}
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println("A soma dos multiplos de 3 e 5 abaixo de 10 é: " + somarMultiplos(10));
	}

}
