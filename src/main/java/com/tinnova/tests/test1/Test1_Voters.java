package com.tinnova.tests.test1;

public class Test1_Voters {
	
	public static void main(String[] args) {
		
		Votes votes = new Votes(1000d);
		
		votes.setValidVotes(800d);
		votes.setBlankVotes(150d);
		votes.setNullVotes(50d);
		
		System.out.println("O percentual de votos válidos é de: " + votes.getPercentageValidVotes() + "%");
		System.out.println("O percentual de votos brancos é de: " + votes.getPercentageBlankVotes() + "%");
		System.out.println("O percentual de votos nulos é de: " + votes.getPercentageNullVotes() + "%");
	}

}
