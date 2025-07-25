
package com.standard.carrinho.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private String id;
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho() {}

    public Carrinho(String id) {
            this.id = id;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public double getTotal() {
        return itens.stream().mapToDouble(ItemCarrinho::getTotal).sum();
    }

    public String getId() { return id; }
    public List<ItemCarrinho> getItens() { return itens; }
}
