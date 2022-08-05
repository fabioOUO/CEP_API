package com.ecommerce.loja.Service;

import com.ecommerce.loja.model.Cliente;
import org.springframework.data.repository.CrudRepository;


/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 */
public interface ClienteService{

	Iterable<Cliente> buscarTodos();

	Cliente buscarPorId(Long id);

	Cliente inserir(Cliente cliente);

	Cliente atualizar(Long id, Cliente cliente);

	Cliente deletar(Long id);

}
