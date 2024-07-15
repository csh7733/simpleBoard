package OAuth.practice.controller;

import OAuth.practice.config.auth.LoginUser;
import OAuth.practice.config.auth.dto.SessionUser;
import OAuth.practice.domain.posts.Posts;
import OAuth.practice.dto.posts.PostsResponseDto;
import OAuth.practice.service.PostsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "0") int page) {
        Page<Posts> allPage = postsService.findAllByPaging(page);
        int totalPages = allPage.getTotalPages();
        int currentGroup = (page / 10) * 10;
        int startPage = currentGroup;
        int endPage = Math.min(startPage + 9, totalPages - 1);
        model.addAttribute("posts", allPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}