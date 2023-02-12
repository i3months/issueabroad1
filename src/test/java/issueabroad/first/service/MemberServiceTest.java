package issueabroad.first.service;

import issueabroad.first.entity.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testFindByEmail() {
        Optional<Member> res = memberService.getMemberByEmail("user1002@naver.com");
        Optional<Member> res2 = memberService.getMemberByEmail("user100@naver.com");

        System.out.println("-------------");
        System.out.println(ObjectUtils.isEmpty(res)); // true
        System.out.println(ObjectUtils.isEmpty(res2)); // false
        System.out.println("----------------");

    }


}