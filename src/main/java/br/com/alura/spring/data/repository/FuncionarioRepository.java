package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.spring.data.orm.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

}
