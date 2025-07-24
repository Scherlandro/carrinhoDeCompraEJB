
package com.standard.carrinho.domain.model;

public class ItemCarrinho {
    private Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return produto.getPreco() * quantidade;
    }
}
