package issueabroad.first.service;

import issueabroad.first.dto.ArticleDTO;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.entity.article.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void testModify() {
        ArticleDTO articleDTO = ArticleDTO.builder()
                .ano(3L)
                .title("변경된 제목입니다...")
                .content("변경된 내용입니다...")
                .build();
        articleService.modify(articleDTO);
    }

    @Test
    public void testDelete() {
        Long ano = 94L;
        articleService.removeWithReplies(ano);
    }

    @Test
    public void testRead() {
        Long ano = 95L;
        ArticleDTO articleDTO = articleService.get(ano);

        System.out.println(articleDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ArticleDTO, Object[]> res = articleService.getList(pageRequestDTO);


        for(ArticleDTO k : res.getDtoList()) {
            System.out.println("res : " + k);
        }
    }

    @Test
    public void testRegister() {
        ArticleDTO dto = ArticleDTO.builder()
                .title("For Test Title ...")
                .content("For Test Content ...")
                .writerEmail("asdf101@naver.com")
                .build();

        Long ano = articleService.register(dto);
    }

}