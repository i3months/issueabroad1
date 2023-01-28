package issueabroad.first.service;

import issueabroad.first.dto.ScrapDTO;
import issueabroad.first.entity.article.Scrap;
import issueabroad.first.entity.member.Member;

public interface ScrapService {

    default ScrapDTO entityToDTO(Scrap scrap, Long replyCount) {
        ScrapDTO scrapDTO = ScrapDTO.builder()
                .sno(scrap.getSno())
                .title(scrap.getTitle())
                .content(scrap.getContent())
                .originContent(scrap.getOriginContent())
                .originTitle(scrap.getOriginTitle())
                .regDate(scrap.getRegDate())
                .modDate(scrap.getModDate())
                .viewCount(scrap.getViewCount())
                .type(scrap.getType())
                .replyCount(replyCount.intValue())
                .build();

        return scrapDTO;
    }

    default Scrap dtoToEntity(ScrapDTO dto) {
        Scrap scrap = Scrap.builder()
                .sno(dto.getSno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .originContent(dto.getOriginContent())
                .originTitle(dto.getOriginTitle())
                .build();

        return scrap;
    }



}
