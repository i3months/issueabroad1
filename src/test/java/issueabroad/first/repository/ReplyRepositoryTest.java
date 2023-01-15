package issueabroad.first.repository;

import issueabroad.first.entity.article.Article;
import issueabroad.first.entity.reply.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void tt() {
        System.out.println(articleRepository.findAll());
    }

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Long id = (long)(Math.random() * 100) + 1;
//            Article article = Article.builder().ano(id).build();
            Optional<Article> article = articleRepository.findById(id);

            Reply reply = Reply.builder()
                    .text("Reply" + i)
                    .article(article.get())
                    .replyer("anonymous")
                    .build();

            replyRepository.save(reply);
        });
    }

}