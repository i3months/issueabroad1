package issueabroad.first.service;

import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements  MemberService{

    private final MemberRepository memberRepository;
    @Override
    public Optional<Member> getMemberByEmail(String email) {
        Optional<Member> res = memberRepository.findMemberByEmail(email);

        return res;
    }
}
