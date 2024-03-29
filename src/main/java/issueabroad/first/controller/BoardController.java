package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.security.dto.AuthMemberDTO;
import issueabroad.first.service.ScrapService;
import issueabroad.first.service.WebUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final WebUserService webUserService;
    private final ScrapService scrapService;

    @GetMapping("/all")
    public String all(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", scrapService.getList(pageRequestDTO));
        model.addAttribute("title", "최신");
        model.addAttribute("url", "scrap");
        model.addAttribute("paging", "all");

        return "scrapBoard";
    }

    @GetMapping("/week")
    public String week(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", scrapService.getListViewCount(pageRequestDTO));
        model.addAttribute("title", "일주일간");
        model.addAttribute("url", "scrap");
        model.addAttribute("paging", "week");

        return "scrapBoard";
    }

    @GetMapping("/america")
    public String america(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", scrapService.getListAmerica(pageRequestDTO));
        model.addAttribute("title", "미국");
        model.addAttribute("url", "scrap");
        model.addAttribute("paging", "america");

        return "scrapBoard";
    }

    @GetMapping("/japan")
    public String japan(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", scrapService.getListJapan(pageRequestDTO));
        model.addAttribute("title", "일본");
        model.addAttribute("url", "scrap");
        model.addAttribute("paging", "japan");

        return "scrapBoard";
    }

    @GetMapping("/free")
    public String free(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", webUserService.getListFree(pageRequestDTO));
        model.addAttribute("title", "자유");
        model.addAttribute("url", "user");
        model.addAttribute("paging", "free");

        return "userBoard";
    }

    @GetMapping("/suggest")
    public String suggest(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", webUserService.getListSuggest(pageRequestDTO));
        model.addAttribute("title", "건의");
        model.addAttribute("url", "user");
        model.addAttribute("paging", "suggest");

        return "userBoard";
    }

}
