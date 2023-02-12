package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.entity.member.Member;
import issueabroad.first.entity.member.MemberForm;
import issueabroad.first.entity.member.MemberRole;
import issueabroad.first.repository.MemberRepository;
import issueabroad.first.repository.WebUserRepository;
import issueabroad.first.security.dto.AuthMemberDTO;
import issueabroad.first.service.MemberService;
import issueabroad.first.service.WebUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final WebUserService webUserService;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/myarticle")
    public String myArticle(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", webUserService.getListMyArticle(authMemberDTO.getEmail(), pageRequestDTO));
        model.addAttribute("url", "user");
        model.addAttribute("paging", "myarticle");

        return "my/myArticle";
    }

    @GetMapping("/myprofile")
    public String myProfile(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        model.addAttribute("id", authMemberDTO.getName());
        model.addAttribute("email", authMemberDTO.getEmail());

        return "my/myProfile";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

    @GetMapping("/members/new")
    public String createForm(@ModelAttribute("member") MemberForm member) {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@ModelAttribute("member") MemberForm member, BindingResult bindingResult) {

        if (ObjectUtils.isEmpty(member.getEmail())) {
            bindingResult.addError(new FieldError("member", "email", "이메일을 입력해주세요."));
        }

        if (!ObjectUtils.isEmpty(member.getEmail()) && !ObjectUtils.isEmpty(memberService.getMemberByEmail(member.getEmail()))) {
            bindingResult.addError(new FieldError("member", "email", "이미 존재하는 이메일입니다."));
        }

        String pattern = "(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}";

        if (ObjectUtils.isEmpty(member.getPassword()) || !(member.getPassword().matches(pattern))) {
            bindingResult.addError(new FieldError("member", "password", "비밀번호는 특수문자와 영문자가 포함된 8~15 길이의 문자열입니다."));
        }

        if (member.getName().length() >= 16 || ObjectUtils.isEmpty(member.getName())) {
            bindingResult.addError(new FieldError("member", "name", "아이디는 15자 이하의 영문 소문자 및 숫자로 구성됩니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("error... {}", bindingResult);
            return "members/createMemberForm";
        }

        Member newMember = Member.builder()
                .email(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .name(member.getName())
                .fromSocial(false)
                .build();
        newMember.addMemberRole(MemberRole.USER);

        memberRepository.save(newMember);

        return "redirect:/";
    }

    @GetMapping("/members/find")
    public String findHome() { // model 으로

        return "members/findHome";
    }

    @GetMapping("/members/findId")
    public String findId() {

        return "members/findId";
    }

    @GetMapping("/members/findPassword")
    public String findPassword() {

        return "members/findPassword";
    }
}
