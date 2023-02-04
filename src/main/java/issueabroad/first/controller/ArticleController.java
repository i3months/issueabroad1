package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.ScrapDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.service.ScrapReplyService;
import issueabroad.first.service.ScrapService;
import issueabroad.first.service.UserReplyService;
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
    private final UserReplyService userReplyService;
    private final ScrapReplyService scrapReplyService;

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
        model.addAttribute("dtoReply", userReplyService.getList(uno));


        return "userArticle";
    }


    @PostMapping("/user/modify/{uno}")
    public String modifyUser(@PathVariable("uno") Long uno, UserDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {
        userService.modify(dto);

        System.out.println("0-------------0");
        System.out.println(uno);
        System.out.println("0-------------0");

        return "redirect:/";
    }

    @GetMapping("/user/modify/{uno}")
    public String modifyUser(@PathVariable("uno") Long uno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        UserDTO dto = userService.get(uno);

        model.addAttribute("dto", dto);

        return "modifyArticle";
    }

    @PostMapping("/user/remove/{uno}")
    public String removeUser(@PathVariable("uno") Long uno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {
        userService.removeWithReplies(uno);

        return "redirect:/";
    }



    @GetMapping("/scrap/read/{sno}")
    public String readScrap(@PathVariable("sno") Long sno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        ScrapDTO dto = scrapService.get(sno);
        scrapService.updateViewCount(sno);

        model.addAttribute("dto", dto);
        model.addAttribute("dtoReply", scrapReplyService.getList(sno));

        return "scrapArticle";
    }

}
