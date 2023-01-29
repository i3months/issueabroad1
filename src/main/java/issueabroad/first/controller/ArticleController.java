package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.service.ScrapService;
import issueabroad.first.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final UserService userService;
    private final ScrapService scrapService;

    @GetMapping("/register")
    public String registerArticle() {
        log.info("register article...");

        return "registerArticle";
    }

    @PostMapping("/register")
    public String registerPost(UserDTO dto, RedirectAttributes redirectAttributes) {
        dto.setViewCount(0l);
        log.info("New article.." + dto);

        Long uno = userService.register(dto);

        return "redirect:/";
    }

    @GetMapping("/user/read/{uno}")
    public String readUser(@PathVariable("uno") Long uno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        UserDTO dto = userService.get(uno);
        userService.updateViewCount(uno);

        model.addAttribute("dto", dto);

        return "userArticle";
    }



//    @GetMapping("/crawl/read/{id}")
//    public String readCrawl(@PathVariable("id") Long id, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
//        CrawlDTO dto = crawlService.read(id);
//        model.addAttribute("dto", dto);
//
//        crawlService.updateViewCount(id);
//
//        return "crawlArticle";
//    }
//
//    @GetMapping("/user/read/{id}")
//    public String readUser(@PathVariable("id") Long id, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
//        UserDTO dto = userService.read(id);
//        model.addAttribute("dto", dto);
//
//        return "userArticle";
//    }

}
