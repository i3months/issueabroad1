package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.service.ScrapService;
import issueabroad.first.service.WebUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private final ScrapService scrapService;
    private final WebUserService webUserService;

    @GetMapping("/")
    public String home2(PageRequestDTO pageRequestDTO, Model model) {
        log.info("Home Controller called : " + pageRequestDTO);

        model.addAttribute("ScrapURL", "scrap");
        model.addAttribute("UserURL", "user");

        model.addAttribute("all", scrapService.getListMain(pageRequestDTO));
        model.addAttribute("week", scrapService.getListViewCountMain(pageRequestDTO));

        model.addAttribute("america", scrapService.getListMainAmerica(pageRequestDTO));
        model.addAttribute("japan", scrapService.getListMainJapan(pageRequestDTO));

        model.addAttribute("free", webUserService.getListMainFree(pageRequestDTO));
        model.addAttribute("suggest", webUserService.getListMainSuggest(pageRequestDTO));


        return "mainBeforeLogin";
    }

    @GetMapping("/home")
    public String homeRedirect() {

        return "redirect:/";
    }

}
