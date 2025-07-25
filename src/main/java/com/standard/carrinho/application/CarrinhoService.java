
package com.standard.carrinho.application;



import com.standard.carrinho.domain.model.Carrinho;
import com.standard.carrinho.domain.model.Produto;
import com.standard.carrinho.domain.port.CarrinhoRepository;
import com.standard.carrinho.messaging.CarrinhoEventProducer;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@Stateless
public class CarrinhoService {

    @Inject
    private CarrinhoRepository repository;

    @Inject
    private CarrinhoEventProducer eventProducer;

    @Transactional
    public Carrinho criarCarrinho(String id) {
        Carrinho c = new Carrinho(id);
        repository.salvar(c);
        eventProducer.notificarCriacao(c);
        return c;
    }

    @Transactional
    public void adicionarProduto(String carrinhoId, Produto produto, int quantidade) {
        Carrinho c = repository.buscar(carrinhoId);
        if (c == null) {
            throw new IllegalArgumentException("Carrinho não encontrado");
        }
        c.adicionarProduto(produto, quantidade);
        repository.salvar(c);
    }

    public Carrinho buscarCarrinho(String id) {
         return repository.buscar(id);
    }
}
