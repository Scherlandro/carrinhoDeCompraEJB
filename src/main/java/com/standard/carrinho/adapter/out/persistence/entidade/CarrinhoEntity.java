package com.standard.carrinho.adapter.out.persistence.entidade;


import com.standard.carrinho.domain.model.Carrinho;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "carrinho")
public class CarrinhoEntity {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrinho_id")
    private List<ItemCarrinhoEntity> itens = new ArrayList<>();

    public CarrinhoEntity() {}

    public CarrinhoEntity(String id) {
        this.id = id;
    }

    public static CarrinhoEntity fromDomain(Carrinho carrinho) {
        CarrinhoEntity entity = new CarrinhoEntity(carrinho.getId());
        entity.itens = carrinho.getItens().stream()
                .map(ItemCarrinhoEntity::fromDomain)
                .collect(Collectors.toList());
        return entity;
    }

    public Carrinho toDomain() {
        Carrinho c = new Carrinho(id);
        itens.forEach(i -> c.adicionarProduto(i.toDomain().getProduto(), i.toDomain().getQuantidade()));
        return c;
    }

    // getters e setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<ItemCarrinhoEntity> getItens() { return itens; }
    public void setItens(List<ItemCarrinhoEntity> itens) { this.itens = itens; }
}
