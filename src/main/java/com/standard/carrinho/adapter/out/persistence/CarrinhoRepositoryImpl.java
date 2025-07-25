package com.standard.carrinho.adapter.out.persistence;

import com.standard.carrinho.adapter.out.persistence.entidade.CarrinhoEntity;
import com.standard.carrinho.domain.model.Carrinho;
import com.standard.carrinho.domain.port.CarrinhoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CarrinhoRepositoryImpl implements CarrinhoRepository {

    @PersistenceContext(unitName = "CarrinhoPU")
    private EntityManager em;

    @Override
    public Carrinho buscar(String id) {
        // conversão da entidade para o domínio
        return em.find(CarrinhoEntity.class, id).toDomain();
    }

    @Override
    public void salvar(Carrinho carrinho) {
        em.merge(CarrinhoEntity.fromDomain(carrinho));
    }
}
