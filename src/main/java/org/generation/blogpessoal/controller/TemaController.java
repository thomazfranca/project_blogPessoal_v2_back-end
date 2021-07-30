package org.generation.blogpessoal.controller;

import java.util.List;

import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<List<Tema>> pegarTodosTemas() {
		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Tema> pegarPorId(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> pegarTemaNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}

	@PostMapping("/postarTema")
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}

	@PutMapping("/atualizarTema")
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema) {
		return ResponseEntity.ok(repository.save(tema));
	}

	@DeleteMapping("/id/{id}")
	public void deletePorId(@PathVariable Long id) {
		repository.deleteById(id);
	}
}