
package com.standard.carrinho.application;

import com.standard.carrinho.domain.model.Carrinho;
import com.standard.carrinho.domain.model.Produto;
import com.standard.carrinho.domain.port.CarrinhoRepository;
import com.standard.carrinho.messaging.CarrinhoEventProducer;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import javax.ejb.Remove;

@Stateful
public class CarrinhoService {

    @Inject
    private CarrinhoRepository repository;

    @Inject
    private CarrinhoEventProducer eventProducer;

    private Carrinho carrinhoAtual;

    @Transactional
    public Carrinho criarCarrinho(String id) {
        this.carrinhoAtual = new Carrinho(id);
        repository.salvar(carrinhoAtual);
        eventProducer.notificarCriacao(carrinhoAtual);
        return carrinhoAtual;
    }

    @Transactional
    public void adicionarProduto(String carrinhoId, Produto produto, int quantidade) {
        if (carrinhoAtual == null || !carrinhoAtual.getId().equals(carrinhoId)) {
            carrinhoAtual = repository.buscar(carrinhoId);
            if (carrinhoAtual == null) {
                throw new IllegalArgumentException("Carrinho não encontrado");
            }
        }
        carrinhoAtual.adicionarProduto(produto, quantidade);
        repository.salvar(carrinhoAtual);
    }

    public Carrinho buscarCarrinho(String id) {
        if (carrinhoAtual != null && carrinhoAtual.getId().equals(id)) {
            return carrinhoAtual;
        }
        return repository.buscar(id);
    }

    @Remove
    public void finalizar() {
        // Método para limpar o estado quando a conversação terminar
        carrinhoAtual = null;
    }
}