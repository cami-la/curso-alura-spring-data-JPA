package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private final CargoRepository cargoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
		this.cargoRepository = cargoRepository;

	}

	public void inicial(Scanner scanner) {
		System.out.println("Qual ação deseja executar");
		System.out.println("0 - Sair");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar");
		System.out.println("3 - Visualizar");
		System.out.println("4 - Detelar");

		int action = scanner.nextInt();
		switch (action) {
		case 1:
			salvar(scanner);
			break;
		case 2:
			atualizar(scanner);
			break;
		case 3: visualizar(); break;
		case 4: deletar(scanner); break;
		default:
			break;
		}
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int idFuncionario = scanner.nextInt();
		
		this.funcionarioRepository.deleteById(idFuncionario);
		
		System.out.println("Deletado");
	}

	private void visualizar() {
		Iterable<Funcionario> funcionarios = this.funcionarioRepository.findAll();
		funcionarios.forEach(System.out::println);
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();
        
        System.out.println(">>>>> Lista de Cargos <<<<<");
        Iterable<Cargo> cargos = this.cargoRepository.findAll();
        cargos.forEach(System.out::println);
        
        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
	}

	private void salvar(Scanner scanner) {
		System.out.println("Nome");
		String nome = scanner.next();

		System.out.println("CPF");
		String cpf = scanner.next();

		System.out.println("Salário");
		BigDecimal salario = scanner.nextBigDecimal();

		System.out.println("Data de Contratação");
		String dataContratacao = scanner.next();
		
		System.out.println(">>>>> Lista de Cargos <<<<<");
        Iterable<Cargo> cargos = this.cargoRepository.findAll();
        cargos.forEach(System.out::println);
        
		System.out.println("Cargo id");
		Integer cargoId = scanner.nextInt();

		List<UnidadeTrabalho> unidades = unidade(scanner);
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadesTrabalho(unidades);

		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}
	
	
	private List<UnidadeTrabalho> unidade(Scanner scanner) {
		
		List<UnidadeTrabalho> unidades = new ArrayList<UnidadeTrabalho>();
		
		while (true) {
			System.out.println("Id Unidade de Trabalho (0 - Sair)");
			Integer unidadeId = scanner.nextInt();

			if (unidadeId == 0)
				break;
			else {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			}
		}

		return unidades;
	}

}
