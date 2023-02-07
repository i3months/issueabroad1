package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.ScrapDTO;
import issueabroad.first.entity.article.Scrap;
import org.springframework.transaction.annotation.Transactional;

public interface ScrapService {

    PageResultDTO<ScrapDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListViewCount(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListViewCountMain(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListAmerica(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListMainAmerica(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListJapan(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListMainJapan(PageRequestDTO pageRequestDTO);
    PageResultDTO<ScrapDTO, Object[]> getListSearch(PageRequestDTO pageRequestDTO);

    @Transactional
    public int updateViewCount(Long sno);

    ScrapDTO get(Long sno);
    void removeWithReplies(Long sno);

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
                .url(scrap.getUrl())
                .webSite(scrap.getWebSite())
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
