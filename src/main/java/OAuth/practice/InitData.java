package OAuth.practice;

import OAuth.practice.domain.posts.Posts;
import OAuth.practice.dto.posts.PostsSaveRequestDto;
import OAuth.practice.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class InitData {

    private final PostsService postsService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        for(int i=1;i<=163;i++){
            PostsSaveRequestDto post = PostsSaveRequestDto.builder()
                    .title("Title " + i)
                    .content("Content " + i)
                    .author("author " + i)
                    .build();
            postsService.save(post);
        }

    }

}
