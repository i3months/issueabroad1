package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.ScrapDTO;
import issueabroad.first.dto.WebUserDTO;
import issueabroad.first.security.dto.AuthMemberDTO;
import issueabroad.first.service.ScrapReplyService;
import issueabroad.first.service.ScrapService;
import issueabroad.first.service.WebUserReplyService;
import issueabroad.first.service.WebUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final WebUserService webUserService;
    private final ScrapService scrapService;
    private final WebUserReplyService webUserReplyService;
    private final ScrapReplyService scrapReplyService;

    @GetMapping("/register")
    public String registerArticle(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        log.info("register article...");

        return "registerArticle";
    }

    @PostMapping("/register")
    public String registerPost(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, WebUserDTO dto, RedirectAttributes redirectAttributes) {
        dto.setViewCount(0l);
        dto.setWriterEmail(authMemberDTO.getEmail());

        log.info("New article.." + dto);

        Long uno = webUserService.register(dto);

        return "redirect:/";
    }

    @GetMapping("/user/read/{uno}")
    public String readUser(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("uno") Long uno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        WebUserDTO dto = webUserService.get(uno);
        webUserService.updateViewCount(uno);

        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("replyerEmail", authMemberDTO.getEmail());
        model.addAttribute("dto", dto);
        model.addAttribute("dtoReply", webUserReplyService.getList(uno));


        return "userArticle";
    }


    @PostMapping("/user/modify/{uno}")
    public String modifyUser(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("uno") Long uno, WebUserDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {

        webUserService.modify(dto);

        return "redirect:/";
    }

    @GetMapping("/user/modify/{uno}")
    public String modifyUser(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("uno") Long uno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        WebUserDTO dto = webUserService.get(uno);

        model.addAttribute("dto", dto);

        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        return "modifyArticle";
    }

    @PostMapping("/user/remove/{uno}")
    public String removeUser(@PathVariable("uno") Long uno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {
        webUserService.removeWithReplies(uno);

        return "redirect:/";
    }



    @GetMapping("/scrap/read/{sno}")
    public String readScrap(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("sno") Long sno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        ScrapDTO dto = scrapService.get(sno);
        scrapService.updateViewCount(sno);


        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("replyerEmail", authMemberDTO.getEmail());
        model.addAttribute("dto", dto);
        model.addAttribute("dtoReply", scrapReplyService.getList(sno));

        return "scrapArticle";
    }

}
