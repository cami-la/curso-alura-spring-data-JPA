package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		System.out.println("Qual ação deseja executar");
		System.out.println("0 - Sair");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar");
		System.out.println("3 - Visualizar");
		System.out.println("4 - Deletar");

		int action = scanner.nextInt();
		switch (action) {
		case 1:
			salvar(scanner);
			break;
		case 2:
			atualizar(scanner);
			break;
		case 3:
			visualizar();
			break;
		case 4:
			deletar(scanner);
		default:
			break;
		}
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		
		this.unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado!");
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadesTrabalho = this.unidadeTrabalhoRepository.findAll();
		unidadesTrabalho.forEach(System.out::println);
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();

		System.out.println("Unidade de Trabalho");
		String descricao = scanner.next();

		System.out.println("Endereço");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeDeTrabalho = new UnidadeTrabalho();
		unidadeDeTrabalho.setDescricao(descricao);
		unidadeDeTrabalho.setEndereco(endereco);
	}

	private void salvar(Scanner scanner) {
		System.out.println("Unidade de Trabalho");
		String descricao = scanner.next();

		System.out.println("Endereço");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeDeTrabalho = new UnidadeTrabalho();
		unidadeDeTrabalho.setDescricao(descricao);
		unidadeDeTrabalho.setEndereco(endereco);

		this.unidadeTrabalhoRepository.save(unidadeDeTrabalho);
		System.out.println("Salvo");
	}

}
