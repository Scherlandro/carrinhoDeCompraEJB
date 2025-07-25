
package com.standard.carrinho.adapter.in.rest;


import com.standard.carrinho.application.CarrinhoService;
import com.standard.carrinho.domain.dto.CarrinhoDto;
import com.standard.carrinho.domain.dto.ProdutoDto;
import com.standard.carrinho.domain.model.Carrinho;
import com.standard.carrinho.domain.model.Produto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

    @Inject
    private CarrinhoService service;

    @POST
    public Carrinho criar(CarrinhoDto dto) {
       return service.criarCarrinho(dto.id());
    }

    @POST
    @Path("/{id}/produto")
    public void adicionar(@PathParam("id") String id, ProdutoDto dto) {
        Produto p = new Produto(dto.id(), dto.nome(), dto.preco());
        service.adicionarProduto(id, p, dto.quantidade());
    }

    @GET
    @Path("/{id}")
    public Carrinho buscar(@PathParam("id") String id) {
        return service.buscarCarrinho(id);
    }

}
