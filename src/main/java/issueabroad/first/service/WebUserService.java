package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.WebUserDTO;
import issueabroad.first.entity.article.WebUser;
import issueabroad.first.entity.member.Member;
import issueabroad.first.security.dto.AuthMemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;

public interface WebUserService {
    Long register(WebUserDTO dto);

    PageResultDTO<WebUserDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    PageResultDTO<WebUserDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO);
    PageResultDTO<WebUserDTO, Object[]> getListSuggest(PageRequestDTO pageRequestDTO);
    PageResultDTO<WebUserDTO, Object[]> getListMainSuggest(PageRequestDTO pageRequestDTO);
    PageResultDTO<WebUserDTO, Object[]> getListFree(PageRequestDTO pageRequestDTO);
    PageResultDTO<WebUserDTO, Object[]> getListMainFree(PageRequestDTO pageRequestDTO);
    PageResultDTO<WebUserDTO, Object[]> getListMyArticle(String email, PageRequestDTO pageRequestDTO);


    @Transactional
    public int updateViewCount(Long uno);

    WebUserDTO get(Long uno);
    void removeWithReplies(Long uno);
    void modify(WebUserDTO webUserDTO);

    default WebUserDTO entityToDTO(WebUser webUser, Member member, Long replyCount) {
        WebUserDTO webUserDTO = WebUserDTO.builder()
                .uno(webUser.getUno())
                .title(webUser.getTitle())
                .content(webUser.getContent())
                .regDate(webUser.getRegDate())
                .modDate(webUser.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .viewCount(webUser.getViewCount())
                .type(webUser.getType())
                .replyCount(replyCount.intValue())
                .build();

        return webUserDTO;

    }

    default WebUser dtoToEntity(WebUserDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        WebUser webUser = WebUser.builder()
                .uno(dto.getUno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .type(dto.getType())
                .viewCount(dto.getViewCount())
                .build();

        return webUser;
    }


}
