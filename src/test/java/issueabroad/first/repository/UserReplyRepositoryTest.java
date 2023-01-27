package issueabroad.first.repository;

import issueabroad.first.entity.article.User;
import issueabroad.first.entity.reply.UserReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserReplyRepositoryTest {

    @Autowired
    private UserReplyRepository userReplyRepository;

    @Test
    public void insertUserReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long uno = (long)(Math.random() * 100) + 1;

            User user = User.builder()
                    .uno(uno)
                    .build();

            UserReply userReply = UserReply.builder()
                    .text("UserReplyText...." + i)
                    .user(user)
                    .replyer("익명")
                    .build();

            userReplyRepository.save(userReply);
        });
    }

}