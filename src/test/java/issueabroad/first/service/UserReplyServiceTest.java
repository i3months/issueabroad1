package issueabroad.first.service;

import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.entity.reply.UserReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserReplyServiceTest {

    @Autowired
    private UserReplyService userReplyService;

    @Test
    public void testReadReplies() {
        int idx = 1;

        List<UserReplyDTO> res = userReplyService.getAllReplyByUno(198l);

        for(UserReplyDTO k : res) {
            System.out.println(idx + " : " + k);
            idx++;
        }
    }

    @Test
    public void testReadReplies2() {
        int idx = 1;

        List<UserReplyDTO> res = userReplyService.getList(197l);

        for(UserReplyDTO k : res) {
            System.out.println(idx + " : " + k);
            idx++;
        }
    }

}