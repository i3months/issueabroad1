package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.repository.search.SearchScrapRepository;
import issueabroad.first.security.dto.AuthMemberDTO;
import issueabroad.first.service.ScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Log4j2
public class SearchController {

    private final ScrapService scrapService;

    @GetMapping("/search/{keyword}")
    public String search2(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @PathVariable("keyword") String keyword, PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setKeyword(keyword);

        model.addAttribute("memberEmail", authMemberDTO.getEmail());
        model.addAttribute("memberName", authMemberDTO.getName());

        model.addAttribute("result", scrapService.getListSearch(pageRequestDTO));
        model.addAttribute("title", keyword);
        model.addAttribute("url", "scrap");
        model.addAttribute("paging", "search/" + keyword);

        return "search/searchResult";
    }
}
