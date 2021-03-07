package br.com.alura.spring.data.teste;

import java.util.Scanner;

public class teste {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o Cargo: ");
		String cargo = scanner.nextLine();
		
		System.out.println("Digite sua idade: ");
		int idade = scanner.nextInt();
		
		System.out.println("O cargo: " + cargo + " idade: " + idade);
	}
	

}
