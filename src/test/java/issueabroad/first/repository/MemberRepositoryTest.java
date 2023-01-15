package issueabroad.first.repository;

import issueabroad.first.entity.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("asdf" + i + "@naver.com")
                    .password("asdfzcv")
                    .name("asdf" + i)
                    .build();

            memberRepository.save(member);
        });
    }

}