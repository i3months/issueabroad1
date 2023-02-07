package issueabroad.first.repository;

import issueabroad.first.entity.article.Scrap;
import issueabroad.first.entity.reply.ScrapReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

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

    @Test
    public void testReadReply() {
        List<ScrapReply> res = scrapReplyRepository.getRepliesByScrapOrderBySrno(
                Scrap.builder().sno(200l).build()
        );

        res.forEach(k -> System.out.println(k));
    }


}