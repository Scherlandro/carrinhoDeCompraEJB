package com.standard.carrinho.adapter.out.persistence.entidade;


import com.standard.carrinho.domain.model.ItemCarrinho;
import com.standard.carrinho.domain.model.Produto;

import javax.persistence.*;


@Entity
@Table(name = "item_carrinho")
public class ItemCarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String produtoId;
    private String produtoNome;
    private double produtoPreco;
    private int quantidade;

    public ItemCarrinhoEntity() {}

    public static ItemCarrinhoEntity fromDomain(ItemCarrinho item) {
        ItemCarrinhoEntity entity = new ItemCarrinhoEntity();
        Produto p = item.getProduto();
        entity.produtoId = p.getId();
        entity.produtoNome = p.getNome();
        entity.produtoPreco = p.getPreco();
        entity.quantidade = item.getQuantidade();
        return entity;
    }

    public ItemCarrinho toDomain() {
        Produto p = new Produto(produtoId, produtoNome, produtoPreco);
        return new ItemCarrinho(p, quantidade);
    }

    // getters e setters omitidos para brevidade
}