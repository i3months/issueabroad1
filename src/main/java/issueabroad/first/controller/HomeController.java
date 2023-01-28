//package issueabroad.first.controller;
//
//import issueabroad.first.dto.PageRequestDTO;
//import issueabroad.first.service.ArticleService;
//import issueabroad.first.service.CrawlService;
////import issueabroad.first.service.UserService;
//import issueabroad.first.service.ScrapService;
//import issueabroad.first.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@Log4j2
//@RequiredArgsConstructor
//public class HomeController {
//
//    private final ScrapService scrapService;
//    private final UserService userService;
//
//    @GetMapping("/")
//    public String home2(PageRequestDTO pageRequestDTO, Model model) {
//        log.info("Home Controller called : " + pageRequestDTO);
//
//        model.addAttribute("free", userService.getListMainFree(pageRequestDTO));
//        model.addAttribute("suggest", userService.getListMainSuggest(pageRequestDTO));
//
//
//        return "mainBeforeLogin";
//    }
//
//    @GetMapping("/")
//    public String home(PageRequestDTO pageRequestDTO, Model model) {
//        log.info("home controller");
//
//        model.addAttribute("crawlURL", "crawl");
//        model.addAttribute("userURL", "user");
//
//        model.addAttribute("all", crawlService.getListMain(pageRequestDTO));
//        model.addAttribute("week", crawlService.getListViewCountMain(pageRequestDTO));
//
//        pageRequestDTO.setType("d");
//        pageRequestDTO.setKeyword("미국");
//
//        model.addAttribute("america", crawlService.getListMain(pageRequestDTO));
//
//        pageRequestDTO.setType("d");
//        pageRequestDTO.setKeyword("일본");
//
//        model.addAttribute("japan", crawlService.getListMain(pageRequestDTO));
//
//        pageRequestDTO.setType("d");
//        pageRequestDTO.setKeyword("자유");
//
//        model.addAttribute("free", userService.getListMain(pageRequestDTO));
//
//        pageRequestDTO.setType("d");
//        pageRequestDTO.setKeyword("건의");
//
//        model.addAttribute("suggest", userService.getListMain(pageRequestDTO));
//
//        return "mainBeforeLogin";
//    }
//
//    @GetMapping("/home")
//    public String homeRedirect() {
//
//        return "redirect:/";
//    }
//
//}
