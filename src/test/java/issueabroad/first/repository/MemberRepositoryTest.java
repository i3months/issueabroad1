package issueabroad.first.repository;

import issueabroad.first.entity.member.Member;
import issueabroad.first.entity.member.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .password(passwordEncoder.encode("1234"))
                    .name("user" + i)
                    .fromSocial(false)
                    .build();
            member.addMemberRole(MemberRole.USER);

            memberRepository.save(member);
        });
    }

    @Test
    public void testMemberRead() {
        Optional<Member> res = memberRepository.findByEmail("user100@naver.com", false);

        Member member = res.get();

        System.out.println(member);

    }

}