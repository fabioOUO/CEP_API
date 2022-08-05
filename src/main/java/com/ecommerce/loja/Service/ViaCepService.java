package com.ecommerce.loja.Service;

import com.ecommerce.loja.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API do
 * <b>ViaCEP</b>.
 * 
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 *
 */
public interface ViaCepService {

	Endereco consultarCepAPI(@PathVariable("cep") String cep);

	Iterable<Endereco> buscarTodos();

	Endereco buscarPorCep(String cep);

	Endereco inserir(String cep);

	Endereco atualizar(String cep, Endereco endereco);

	Endereco deletar(String cep);
}
