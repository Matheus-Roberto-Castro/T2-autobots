package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ClienteControle;
import com.autobots.automanager.entidades.Cliente;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<Cliente> {

	 @Autowired
    private AdicionadorLinkDocumento adicionadorDocumento;
    @Autowired
    private AdicionadorLinkTelefone adicionadorTelefone;
    @Autowired
    private AdicionadorLinkEndereco adicionadorEndereco;

	@Override
	public void adicionarLink(List<Cliente> lista) {
		for (Cliente cliente : lista) {
			long id = cliente.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ClienteControle.class)
							.obterCliente(id))
					.withSelfRel();
			cliente.add(linkProprio);

			if (cliente.getEndereco() != null) {
        	    adicionadorEndereco.adicionarLink(cliente.getEndereco());
        	}

        	if (cliente.getDocumentos() != null) {
        	    adicionadorDocumento.adicionarLink(cliente.getDocumentos());
        	}

        	if (cliente.getTelefones() != null) {
        	    adicionadorTelefone.adicionarLink(cliente.getTelefones());
        	}
		}
	}

	@Override
	public void adicionarLink(Cliente objeto) {
		long id = objeto.getId();

    	Link linkSelf = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(ClienteControle.class)
    	                .obterCliente(id))
    	        .withSelfRel();

    	Link linkClientes = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(ClienteControle.class)
    	                .obterClientes())
    	        .withRel("clientes");

    	Link linkUpdate = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(ClienteControle.class)
    	                .atualizarCliente(id, objeto))
    	        .withRel("update");

    	Link linkDelete = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(ClienteControle.class)
    	                .excluirCliente(id))
    	        .withRel("delete");

    	objeto.add(linkSelf, linkClientes, linkUpdate, linkDelete);
	}
}