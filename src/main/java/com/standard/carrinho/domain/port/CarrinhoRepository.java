
package com.standard.carrinho.domain.port;

import com.standard.carrinho.domain.model.Carrinho;

public interface CarrinhoRepository {
    Carrinho buscar(String id);
    void salvar(Carrinho carrinho);
}
