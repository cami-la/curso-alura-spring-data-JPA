package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual funcionário deseja executar: ");
			System.out.println("0 - Sair");
		}
	}

}
