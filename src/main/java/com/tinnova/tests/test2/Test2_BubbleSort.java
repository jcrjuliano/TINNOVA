package com.tinnova.tests.test2;

public class Test2_BubbleSort {
	
	public static void bubbleSort(int[] vetor) {
	    int size = vetor.length;	    
        int temporario;  
         
        for(int i=0; i < size; i++){
        	for(int x=1; x < (size-i); x++){
        		if(vetor[x-1] > vetor[x]){  
        			temporario = vetor[x-1];  
                    vetor[x-1] = vetor[x];  
                    vetor[x] = temporario;
        		}
       		}
       	}
	}
	
	 public static void main(String[] args) {  
         int vetor[] = {5, 3, 2, 4, 7, 1, 0, 6};
         
                 
         bubbleSort(vetor);  

         for(Integer numero: vetor){  
                 System.out.print(numero);  
         }  

 }  
}  

