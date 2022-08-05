package com.ecommerce.loja.Service.impl;

import com.ecommerce.loja.Repository.EnderecoRepository;
import com.ecommerce.loja.Service.ViaCepService;
import com.ecommerce.loja.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoServiceImpl implements ViaCepService {
    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public Endereco consultarCepAPI(String cep) {
        RestTemplate template = new RestTemplate();
        Endereco endereco = template.getForObject("https://viacep.com.br/ws/" + cep + "/json", Endereco.class, cep);
        return endereco;
    }

    @Override
    public Iterable<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco buscarPorCep(String cep) {
        Endereco endereco = null;
        if(enderecoRepository.existsById(cep)){
            endereco = enderecoRepository.findById(cep).get();
        }
        return endereco;
    }

    @Override
    public Endereco inserir(String cep) {
        Endereco inserido;

        if(enderecoRepository.existsById(cep)){
            inserido = null;
        }else{
            inserido = this.consultarCepAPI(cep);
            enderecoRepository.save(inserido);
        }

        return inserido;
    }

    @Override
    public Endereco atualizar(String cep, Endereco endereco) {
        Endereco atualizar = null;
        if(enderecoRepository.existsById(endereco.getCep())){
            enderecoRepository.save(endereco);
            atualizar = endereco;
        }
        return atualizar;
    }

    @Override
    public Endereco deletar(String cep) {
        Endereco deletado = null;
        if(enderecoRepository.existsById(cep)){
            deletado = enderecoRepository.findById(cep).get();
            enderecoRepository.deleteById(cep);
        }
        return deletado;
    }
}
