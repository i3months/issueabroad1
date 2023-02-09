package issueabroad.first.security.service;

import com.querydsl.core.types.dsl.DateTimeOperation;
import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.MemberRepository;
import issueabroad.first.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Service loadUserByUsername : " + username);

        Optional<Member> res = memberRepository.findByEmail(username, false);

        /**
         * 먼저 이메일이 데이터베이스에 있는지 확인한다.
         */

        if(!res.isPresent()) {
            System.out.println(res.get());
            throw new UsernameNotFoundException("이메일이나 소셜 계정 가입 여부를 확인해주세요.");
        }

        /**
         * 데이터베이스에 존재함을 확인했으면 비밀번호 검증
         */

        Member member = res.get();

        log.info("----------");
        log.info("member : " + member);
        log.info("----------");

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toSet())
        );

        authMemberDTO.setName(member.getName());
        authMemberDTO.setFromSocial(member.isFromSocial());

        return authMemberDTO;

    }
}
