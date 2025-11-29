package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.entidades.Endereco;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<Endereco>  {

    @Override
    public void adicionarLink(List<Endereco> lista) {
        for (Endereco endereco : lista) {
            long id = endereco.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(EnderecoControle.class)
                            .obterEndereco(id))
                    .withSelfRel();
            endereco.add(linkProprio);
        }
    }

    @Override
	public void adicionarLink(Endereco objeto) {
		long id = objeto.getId();

    	Link linkSelf = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(EnderecoControle.class)
    	                .obterEndereco(id))
    	        .withSelfRel();

    	Link linkEnderecos = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(EnderecoControle.class)
    	                .obterEnderecos())
    	        .withRel("enderecos");

    	Link linkUpdate = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(EnderecoControle.class)
    	                .atualizarEndereco(id, objeto))
    	        .withRel("update");

    	Link linkDelete = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(EnderecoControle.class)
    	                .excluirEndereco(id))
    	        .withRel("delete");

    	objeto.add(linkSelf, linkEnderecos, linkUpdate, linkDelete);
	}
}
