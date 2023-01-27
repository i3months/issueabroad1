//package issueabroad.first.controller;
//
//import issueabroad.first.dto.CrawlDTO;
//import issueabroad.first.dto.PageRequestDTO;
//import issueabroad.first.dto.UserDTO;
//import issueabroad.first.service.CrawlService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//@RequiredArgsConstructor
//public class ArticleController {
//
////    private final UserService userService;
//    private final CrawlService crawlService;
//
//
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
////        UserDTO dto = userService.read(id);
////        model.addAttribute("dto", dto);
//
//        return "userArticle";
//    }
//
//}
