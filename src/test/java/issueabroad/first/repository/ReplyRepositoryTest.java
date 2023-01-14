package issueabroad.first.repository;

import issueabroad.first.entity.article.Article2;
import issueabroad.first.entity.reply.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long id = (long)(Math.random() * 100) + 1;
            Article2 article2 = Article2.builder().id(id).build();

            Reply reply = Reply.builder()
                    .text("Reply" + i)
                    .article(article2)
                    .writer("anonymous")
                    .build();

            replyRepository.save(reply);
        });
    }

}