package issueabroad.first.service;

import issueabroad.first.dto.ScrapReplyDTO;
import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.entity.article.Scrap;
import issueabroad.first.entity.reply.ScrapReply;

import java.util.List;

public interface ScrapReplyService {

    List<ScrapReplyDTO> getAllReplyBySno(Long sno);
    List<ScrapReplyDTO> getList(Long sno);
    void modify(ScrapReplyDTO scrapReplyDTO);
    void remove(Long sno);
    Long register(ScrapReplyDTO scrapReplyDTO);

    default ScrapReplyDTO entityToDTO(ScrapReply scrapReply) {
        ScrapReplyDTO scrapReplyDTO = ScrapReplyDTO.builder()
                .srno(scrapReply.getSrno())
                .text(scrapReply.getText())
                .replyer(scrapReply.getReplyer())
                .replyerEmail(scrapReply.getReplyerEmail())
                .modDate(scrapReply.getModDate())
                .regDate(scrapReply.getRegDate())
                .build();

        return scrapReplyDTO;
    }

    default ScrapReply dtoToEntity(ScrapReplyDTO dto) {

        Scrap scrap = Scrap.builder().sno(dto.getSno()).build();

        ScrapReply scrapReply = ScrapReply.builder()
                .srno(dto.getSrno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .replyerEmail(dto.getReplyerEmail())
                .scrap(scrap)
                .build();

        return scrapReply;
    }
}
