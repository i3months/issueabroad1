package issueabroad.first.service;

import issueabroad.first.dto.UserDTO;
import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.reply.UserReply;

import java.util.List;

public interface UserReplyService {

    List<UserReplyDTO> getAllReplyByUno(Long uno);

    default UserReplyDTO entityToDTO(UserReply userReply) {
        UserReplyDTO userReplyDTO = UserReplyDTO.builder()
                .urno(userReply.getUrno())
                .text(userReply.getText())
                .replyer(userReply.getReplyer())
                .modDate(userReply.getModDate())
                .regDate(userReply.getRegDate())
                .build();

        return userReplyDTO;
    }

    default UserReply dtoToEntity(UserReplyDTO dto) {
        UserReply userReply = UserReply.builder()
                .urno(dto.getUrno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .build();

        return userReply;
    }


}
