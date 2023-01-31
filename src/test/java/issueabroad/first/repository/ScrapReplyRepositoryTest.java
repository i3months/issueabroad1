package issueabroad.first.repository;

import issueabroad.first.entity.article.Scrap;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.reply.ScrapReply;
import issueabroad.first.entity.reply.UserReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScrapReplyRepositoryTest {

    @Autowired
    private ScrapReplyRepository scrapReplyRepository;


    @Test
    public void insertScrapReply() {
        IntStream.rangeClosed(1, 2000).forEach(i -> {
            long sno = (long)(Math.random() * 200) + 1;

            Scrap scrap = Scrap.builder()
                    .sno(sno)
                    .build();

            ScrapReply scrapReply = ScrapReply.builder()
                    .text("ScrapReply....." + i)
                    .scrap(scrap)
                    .replyer("익명")
                    .build();

            scrapReplyRepository.save(scrapReply);
        });
    }

}