package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.ScrapDTO;
import issueabroad.first.dto.WebUserDTO;
import issueabroad.first.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {

    Optional<Member> getMemberByEmail(String email);
}
