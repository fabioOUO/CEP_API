package com.ecommerce.loja.controller;

import com.ecommerce.loja.Service.impl.EnderecoServiceImpl;
import com.ecommerce.loja.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 */
@RestController
@RequestMapping("enderecos")
public class EnderecoRestController {

	@Autowired
	private EnderecoServiceImpl enderecoService;

	@GetMapping
	public ResponseEntity<Iterable<Endereco>> buscarTodos() {
		return ResponseEntity.ok(enderecoService.buscarTodos());
	}

	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> buscarPorId(@PathVariable String cep) {
		return ResponseEntity.ok(enderecoService.buscarPorCep(cep));
	}

	@PostMapping
	public ResponseEntity<Endereco> inserir(@RequestBody String cep) {
		Endereco inserido =  enderecoService.inserir(cep);
		return ResponseEntity.ok(inserido);
	}

	@PutMapping("/{cep}")
	public ResponseEntity<Endereco> atualizar(@PathVariable String cep, @RequestBody Endereco endereco) {
		Endereco atualizado =  enderecoService.atualizar(cep, endereco);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{cep}")
	public ResponseEntity<Endereco> deletar(@PathVariable String cep) {
		Endereco deletado =  enderecoService.deletar(cep);
		return ResponseEntity.ok(deletado);
	}
}
