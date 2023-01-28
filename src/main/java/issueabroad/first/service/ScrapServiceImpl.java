package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.ScrapDTO;
import issueabroad.first.entity.article.Scrap;
import issueabroad.first.repository.ScrapReplyRepository;
import issueabroad.first.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;
    private final ScrapReplyRepository scrapReplyRepository;

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("sno").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCount(
                pageRequestDTO.getPageableMain(Sort.by("sno").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListViewCount(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("viewCount").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListViewCountMain(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCount(
                pageRequestDTO.getPageableMain(Sort.by("viewCount").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListAmerica(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCountType("미국",
                pageRequestDTO.getPageable(Sort.by("sno").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListMainAmerica(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCountType("미국",
                pageRequestDTO.getPageableMain(Sort.by("sno").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListJapan(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCountType("일본",
                pageRequestDTO.getPageable(Sort.by("sno").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<ScrapDTO, Object[]> getListMainJapan(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ScrapDTO> fn = (en -> entityToDTO((Scrap)en[0], (Long)en[1]));

        Page<Object[]> res = scrapRepository.getScrapWithReplyCountType("일본",
                pageRequestDTO.getPageableMain(Sort.by("sno").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public ScrapDTO get(Long sno) {
        Object res = scrapRepository.getUserBySno(sno);
        Object[] arr = (Object[])res;

        return entityToDTO((Scrap)arr[0], (Long)arr[1]);
    }

    @Override
    public void removeWithReplies(Long sno) {

    }
}
