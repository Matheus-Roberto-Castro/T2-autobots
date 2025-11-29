package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.entidades.Documento;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento>  {

    @Override
    public void adicionarLink(List<Documento> lista) {
        for (Documento documento : lista) {
            long id = documento.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(DocumentoControle.class)
                            .obterDocumento(id))
                    .withSelfRel();
            documento.add(linkProprio);
        }
    }

    @Override
	public void adicionarLink(Documento objeto) {
		long id = objeto.getId();

    	Link linkSelf = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(DocumentoControle.class)
    	                .obterDocumento(id))
    	        .withSelfRel();

    	Link linkDocumentos = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(DocumentoControle.class)
    	                .obterDocumentos())
    	        .withRel("documentos");

    	Link linkUpdate = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(DocumentoControle.class)
    	                .atualizarDocumento(id, objeto))
    	        .withRel("update");

    	Link linkDelete = WebMvcLinkBuilder
    	        .linkTo(WebMvcLinkBuilder
    	                .methodOn(DocumentoControle.class)
    	                .excluirDocumento(id))
    	        .withRel("delete");

    	objeto.add(linkSelf, linkDocumentos, linkUpdate, linkDelete);
	}
}
