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

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.AdicionadorLinkTelefone;
import com.autobots.automanager.modelos.TelefoneAtualizador;
import com.autobots.automanager.modelos.TelefoneSelecionador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
    @Autowired 
    private TelefoneRepositorio repositorio;
    @Autowired
    private TelefoneSelecionador selecionador;
    @Autowired
    private AdicionadorLinkTelefone adicionadorLink;
    

    @GetMapping
    public ResponseEntity<List<Telefone>> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        if (telefones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adicionadorLink.adicionarLink(telefones);
        return ResponseEntity.ok(telefones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable Long id) {
        Telefone telefone = repositorio.findById(id).orElse(null);
        if (telefone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        adicionadorLink.adicionarLink(telefone);
        return ResponseEntity.ok(telefone);
    }

    @PostMapping
    public ResponseEntity<Telefone> cadastrarEndereco(@RequestBody Telefone telefone) {
        Telefone salvo = repositorio.save(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long id, @RequestBody Telefone atualizacao) {
        Telefone telefone = repositorio.findById(id).orElse(null);
		if (telefone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		TelefoneAtualizador atualizador = new TelefoneAtualizador();
		atualizador.atualizar(telefone, atualizacao);
		repositorio.save(telefone);

		return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Telefone> excluirTelefone(@PathVariable Long id) {
		Telefone telefone = repositorio.findById(id).orElse(null);
		if (telefone == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		repositorio.delete(telefone);
		return ResponseEntity.noContent().build();
	}
}
