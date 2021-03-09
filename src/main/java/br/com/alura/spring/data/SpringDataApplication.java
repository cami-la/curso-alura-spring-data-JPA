package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final CrudFuncionarioService funcionarioService;

	private boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeTrabalhoService unidadeDeTrabalhoService, CrudFuncionarioService funcionarioService) {
		this.cargoService = cargoService;
		this.unidadeTrabalhoService = unidadeDeTrabalhoService;
		this.funcionarioService = funcionarioService;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade de Trabalho");
			System.out.println("3 - Funcionário");

			int action = scanner.nextInt();
			if (action == 1) {
				cargoService.inicial(scanner);
			} else if (action == 2) {
				unidadeTrabalhoService.inicial(scanner);
			}else if (action == 3) {
				funcionarioService.inicial(scanner);
			} else {
				system = false;
			}
		}
	}
}
