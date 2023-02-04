package issueabroad.first.service;

import issueabroad.first.dto.ScrapReplyDTO;
import issueabroad.first.entity.reply.ScrapReply;

import java.util.List;

public interface ScrapReplyService {

    List<ScrapReplyDTO> getAllReplyBySno(Long sno);

    default ScrapReplyDTO entityToDTO(ScrapReply scrapReply) {
        ScrapReplyDTO scrapReplyDTO = ScrapReplyDTO.builder()
                .srno(scrapReply.getSrno())
                .text(scrapReply.getText())
                .replyer(scrapReply.getReplyer())
                .modDate(scrapReply.getModDate())
                .regDate(scrapReply.getRegDate())
                .build();

        return scrapReplyDTO;
    }

    default ScrapReply dtoToEntity(ScrapReplyDTO dto) {
        ScrapReply scrapReply = ScrapReply.builder()
                .srno(dto.getSrno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .build();

        return scrapReply;
    }
}
