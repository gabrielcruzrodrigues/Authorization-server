package br.com.gabrielrodrigues.post.api;

import br.com.gabrielrodrigues.post.client.UserClient;
import br.com.gabrielrodrigues.post.domain.Post;
import br.com.gabrielrodrigues.post.exception.ResourceNotFoundException;
import br.com.gabrielrodrigues.post.repository.PostRepository;
import br.com.gabrielrodrigues.post.security.SecurityService;
import br.com.gabrielrodrigues.post.security.accessInterface.CanWritePosts;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostRepository postRepository;
    private final SecurityService securityService;
    private final UserClient userClient;

    public PostController(PostRepository postRepository,
                          SecurityService securityService,
                          UserClient userClient) {
        this.postRepository = postRepository;
        this.securityService = securityService;
        this.userClient = userClient;
    }

    @GetMapping
    public org.springframework.data.domain.Page<PostSummaryResponse> findAll(Pageable pageable) {
        final Page<Post> postPage = postRepository.findAll(pageable);
        final var postResponses = postPage.getContent()
                .stream()
                .map(PostSummaryResponse::of)
                .collect(Collectors.toList());
        return new PageImpl<>(postResponses, pageable, postPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CanWritePosts
    public PostDetailedResponse create(@RequestBody @Valid PostRequest postRequest) {
        final Post post = new Post(securityService.getUserId(), postRequest.getTitle(), postRequest.getContent());
        postRepository.save(post);
        return PostDetailedResponse.of(post);
    }

    @GetMapping("/{id}")
    public PostDetailedResponse findById(@PathVariable Long id) {
        final Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return userClient.findById(post.getEditorId())
                .map(userResponse -> PostDetailedResponse.of(post, EditorResponse.of(userResponse)))
                .orElseGet(() -> PostDetailedResponse.of(post));
    }
}
