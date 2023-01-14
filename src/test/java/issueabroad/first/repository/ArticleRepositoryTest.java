package issueabroad.first.repository;

import issueabroad.first.entity.article.Article2;
import issueabroad.first.entity.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("zz" + i + "@gmail.com")
                    .build();

            Article2 article = Article2.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer(member)
                    .build();

            articleRepository.save(article);
        });
    }

}