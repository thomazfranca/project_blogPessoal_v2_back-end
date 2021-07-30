package org.generation.blogpessoal.controller;

import java.util.List;
import org.generation.blogpessoal.model.Postagem;
import org.generation.blogpessoal.repository.PostagemRepository;
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
@RequestMapping("/api/v1/postagem")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<List<Postagem>> pegarTodasPostagens() {
		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Postagem> pegarPorId(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List <Postagem>> pegarPorTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping("/postar") 
	public ResponseEntity<Postagem> postPostagem (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping("/atualizarPost")
	public ResponseEntity<Postagem> putPostagem (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/id/{id}")
	public void deletePorId(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
