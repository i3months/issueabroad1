package issueabroad.first.service;

import issueabroad.first.dto.UserReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebUserReplyServiceTest {

    @Autowired
    private WebUserReplyService webUserReplyService;

    @Test
    public void testReadReplies() {
        int idx = 1;

        List<UserReplyDTO> res = webUserReplyService.getAllReplyByUno(198l);

        for(UserReplyDTO k : res) {
            System.out.println(idx + " : " + k);
            idx++;
        }
    }

    @Test
    public void testReadReplies2() {
        int idx = 1;

        List<UserReplyDTO> res = webUserReplyService.getList(2l);

        for(UserReplyDTO k : res) {
            System.out.println(idx + " : " + k);
            idx++;
        }
    }

}