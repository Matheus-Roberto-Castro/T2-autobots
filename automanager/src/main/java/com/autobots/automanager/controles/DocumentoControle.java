package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.AdicionadorLinkDocumento;
import com.autobots.automanager.modelos.DocumentoAtualizador;
import com.autobots.automanager.modelos.DocumentoSelecionador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
    @Autowired
    private DocumentoRepositorio repositorio;
    @Autowired
    private DocumentoSelecionador selecionador;
    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @GetMapping
    public ResponseEntity<List<Documento>> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        if (documentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adicionadorLink.adicionarLink(documentos);
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumento(@PathVariable Long id) {
        Documento documento = repositorio.findById(id).orElse(null);
        if (documento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        adicionadorLink.adicionarLink(documento);
        return ResponseEntity.ok(documento);
    }

    @PostMapping
    public ResponseEntity<Documento> cadastrarDocumento(@RequestBody Documento documento) {
        Documento salvo = repositorio.save(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> atualizarDocumento(@PathVariable Long id, @RequestBody Documento atualizacao) {
        Documento documento = repositorio.findById(id).orElse(null);
		if (documento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		DocumentoAtualizador atualizador = new DocumentoAtualizador();
		atualizador.atualizar(documento, atualizacao);
		repositorio.save(documento);

		return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Documento> excluirDocumento(@PathVariable Long id) {
		Documento documento = repositorio.findById(id).orElse(null);
		if (documento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		repositorio.delete(documento);
		return ResponseEntity.noContent().build();
	}
}

