package br.com.gabrielrodrigues.post.repository;

import br.com.gabrielrodrigues.post.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}