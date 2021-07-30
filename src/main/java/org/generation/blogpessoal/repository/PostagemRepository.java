package org.generation.blogpessoal.repository;

import java.util.LinkedList;

import org.generation.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	public LinkedList<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
