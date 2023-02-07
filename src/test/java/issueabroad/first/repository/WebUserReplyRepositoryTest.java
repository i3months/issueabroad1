package issueabroad.first.repository;

import issueabroad.first.entity.article.WebUser;
import issueabroad.first.entity.reply.WebUserReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class WebUserReplyRepositoryTest {

    @Autowired
    private WebUserReplyRepository webUserReplyRepository;

    @Test
    public void insertWebUserReply() {
        IntStream.rangeClosed(1, 1000).forEach(i -> {
            long uno = (long)(Math.random() * 200) + 1;

            WebUser webUser = WebUser.builder()
                    .uno(uno)
                    .build();

            WebUserReply webUserReply = WebUserReply.builder()
                    .text("UserReplyText...." + i)
                    .webUser(webUser)
                    .replyer("익명")
                    .build();

            webUserReplyRepository.save(webUserReply);
        });
    }

    @Test
    public void testReadReply() {
        List<Object[]> res = webUserReplyRepository.getWebUserReplyByUno(198L);

        int idx = 1;

        for(Object[] k : res) {
            System.out.println(idx + " : " + Arrays.toString(k));
            idx++;
        }
    }

    @Test
    public void testReadReply2() {
        List<WebUserReply> res = webUserReplyRepository.getRepliesByWebUserOrderByUrno(
                WebUser.builder().uno(197l).build()
        );

        res.forEach(k -> System.out.println(k));
    }

}