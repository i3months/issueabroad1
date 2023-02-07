package issueabroad.first.service;

import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.entity.article.WebUser;
import issueabroad.first.entity.reply.WebUserReply;

import java.util.List;

public interface WebUserReplyService {

    List<UserReplyDTO> getAllReplyByUno(Long uno);
    List<UserReplyDTO> getList(Long uno);
    void modify(UserReplyDTO userReplyDTO);
    void remove(Long uno);
    Long register(UserReplyDTO userReplyDTO);

    default UserReplyDTO entityToDTO(WebUserReply webUserReply) {
        UserReplyDTO userReplyDTO = UserReplyDTO.builder()
                .urno(webUserReply.getUrno())
                .text(webUserReply.getText())
                .replyer(webUserReply.getReplyer())
                .modDate(webUserReply.getModDate())
                .regDate(webUserReply.getRegDate())
                .build();

        return userReplyDTO;
    }

    default WebUserReply dtoToEntity(UserReplyDTO dto) {

        WebUser webUser = WebUser.builder().uno(dto.getUno()).build();

        WebUserReply webUserReply = WebUserReply.builder()
                .urno(dto.getUrno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .webUser(webUser)
                .build();

        return webUserReply;
    }


}
