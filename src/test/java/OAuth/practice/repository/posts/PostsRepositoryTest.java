package OAuth.practice.repository.posts;

import OAuth.practice.domain.posts.Posts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts save = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        log.info("id = {}",save.getId());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

     @Test
     public void BaseTimeEntity_등록() {
         //given
         LocalDateTime now = LocalDateTime.now();
         Posts save = postsRepository.save(Posts.builder()
                 .title("title")
                 .content("content")
                 .author("author")
                 .build());

         log.info("id = {}",save.getId());
         //when
         List<Posts> postsList = postsRepository.findAll();

         //then
         Posts posts = postsList.get(0);
         log.info("id = {}",posts.getId());

         log.info(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

         assertThat(posts.getCreatedDate()).isAfter(now);
         assertThat(posts.getModifiedDate()).isAfter(now);
     }
}
