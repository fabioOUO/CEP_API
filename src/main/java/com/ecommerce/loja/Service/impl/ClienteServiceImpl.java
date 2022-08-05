package com.ecommerce.loja.Service.impl;



import com.ecommerce.loja.Repository.ClienteRepository;
import com.ecommerce.loja.Service.ClienteService;
import com.ecommerce.loja.Service.ViaCepService;
import com.ecommerce.loja.model.Cliente;
import com.ecommerce.loja.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	EnderecoServiceImpl enderecoService;

	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Cliente> buscarTodos() {
		// Buscar todos os Clientes.
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// Buscar Cliente por ID.

		Cliente cliente = null;

		if(clienteRepository.existsById(id)){
			cliente = clienteRepository.findById(id).get();
		}
		return cliente;
	}

	@Override
	public Cliente inserir(Cliente cliente) {

		Cliente inserido = null;
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoService.buscarPorCep(cep);

		if(endereco == null){
			endereco = enderecoService.inserir(cep);
		}

		cliente.setEndereco(endereco);
		inserido =  clienteRepository.save(cliente);

		return inserido;
	}

	@Override
	public Cliente atualizar(Long id, Cliente cliente) {
		// Buscar Cliente por ID, caso exista:

		Cliente atualizado = null;
		if(clienteRepository.existsById(id)){
			atualizado = this.inserir(cliente);
		}
		return atualizado;
	}

	@Override
	public Cliente deletar(Long id) {
		// Deletar Cliente por ID.

		Cliente deletado = null;
		if(clienteRepository.existsById(id)){
			deletado = clienteRepository.findById(id).get();
			clienteRepository.deleteById(id);
		}
		return deletado;
	}

}
