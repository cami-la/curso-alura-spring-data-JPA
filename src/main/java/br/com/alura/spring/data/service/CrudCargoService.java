package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private Boolean system = true;
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		System.out.println("Qual ação deseja executar");
		System.out.println("0 - Sair");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar");

		int action = scanner.nextInt();
		while (system) {
			switch (action) {
			case 1:
				salvar(scanner);
				system = false;
			case 2:
				atualizar(scanner);
				system = false;
			default:
				system = false;
			}
		}
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();

		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargo.setId(id);

		this.cargoRepository.save(cargo);
		System.out.println("Atualizado");

	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		this.cargoRepository.save(cargo);

		System.out.println("Salvo");
	}

}