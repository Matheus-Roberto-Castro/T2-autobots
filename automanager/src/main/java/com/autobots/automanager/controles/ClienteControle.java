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

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelos.AdicionadorLinkCliente;
import com.autobots.automanager.modelos.ClienteAtualizador;
import com.autobots.automanager.modelos.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {
	@Autowired
	private ClienteRepositorio repositorio;
	@Autowired
	private ClienteSelecionador selecionador;
	@Autowired
	private AdicionadorLinkCliente adicionadorLink;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterCliente(@PathVariable long id) {
		Cliente cliente = repositorio.findById(id).orElse(null);
		if (cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} 
		adicionadorLink.adicionarLink(cliente);
    	return ResponseEntity.ok(cliente);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> obterClientes() {
		List<Cliente> clientes = repositorio.findAll();
		if (clientes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} 

		adicionadorLink.adicionarLink(clientes);
		return ResponseEntity.ok(clientes);
	}

	@PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente salvo = repositorio.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); 
    }

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente atualizacao) {
		Cliente cliente = repositorio.findById(id).orElse(null);
		if (cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		ClienteAtualizador atualizador = new ClienteAtualizador();
		atualizador.atualizar(cliente, atualizacao);
		repositorio.save(cliente);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirCliente(@PathVariable Long id) {
		Cliente cliente = repositorio.findById(id).orElse(null);
		if (cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		repositorio.delete(cliente);
		return ResponseEntity.noContent().build();
	}
}
