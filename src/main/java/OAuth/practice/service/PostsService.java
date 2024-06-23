package OAuth.practice.service;

import OAuth.practice.domain.posts.Posts;
import OAuth.practice.dto.posts.PostsResponseDto;
import OAuth.practice.dto.posts.PostsSaveRequestDto;
import OAuth.practice.dto.posts.PostsUpdateRequestDto;
import OAuth.practice.repository.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
//
//    @Transactional
//    public void delete (Long id) {
//        Posts posts = postsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//
//        postsRepository.delete(posts);
//    }
//
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
//
//    @Transactional(readOnly = true)
//    public List<PostsListResponseDto> findAllDesc() {
//        return postsRepository.findAllDesc().stream()
//                .map(PostsListResponseDto::new)
//                .collect(Collectors.toList());
//    }
}