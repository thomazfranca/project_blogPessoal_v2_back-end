package org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.model.UsuarioLogin;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.generation.blogpessoal.services.UsuarioService;
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
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodosUsuarios () {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Usuario> pegarUsuarioId(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> pegarNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/nomeUsuario/{nomeUsuario}")
	public ResponseEntity<Optional<Usuario>> pegarNomeUsuario(@PathVariable String nomeUsuario) {
		return ResponseEntity.ok(repository.findByNomeUsuario(nomeUsuario));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Object> postCadastrar(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioCriado = usuarioService.CadastrarUsuario(usuario);
		if (usuarioCriado.isEmpty()) {
			return ResponseEntity.status(200).body("Usuario já existente!");
		} else {
			return ResponseEntity.status(201).body(usuarioCriado.get());

		}
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticacaoUsuario(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletarUsuario(@PathVariable long id) {
		Optional<Usuario> verificaId = repository.findById(id);
		if (verificaId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuario não criado!");
		} else {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado");
		}

	}
}
