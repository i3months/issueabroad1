package issueabroad.first.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.WebUserDTO;
import issueabroad.first.entity.article.QWebUser;
import issueabroad.first.entity.article.WebUser;
import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.WebUserReplyRepository;
import issueabroad.first.repository.WebUserRepository;
import issueabroad.first.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class WebUserServiceImpl implements WebUserService {

    private final WebUserRepository webUserRepository;
    private final WebUserReplyRepository replyRepository;

    @Override
    public Long register(WebUserDTO dto) {
        log.info(dto);

        WebUser webUser = dtoToEntity(dto);
        webUserRepository.save(webUser);

        return webUser.getUno();
    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCount(
                pageRequestDTO.getPageableMain(Sort.by("uno").descending()));

        return new PageResultDTO<>(res, fn);

    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getListSuggest(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCountType("건의",
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);

    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getListMainSuggest(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCountType("건의",
                pageRequestDTO.getPageableMain(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getListFree(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCountType("자유",
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getListMainFree(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCountType("자유",
                pageRequestDTO.getPageableMain(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<WebUserDTO, Object[]> getListMyArticle(String email, PageRequestDTO pageRequestDTO) {
        Function<Object[], WebUserDTO> fn = (en -> entityToDTO((WebUser)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = webUserRepository.getWebUserWithReplyCountByEmail(email,
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);

    }

    @Override
    public int updateViewCount(Long uno) {
        return webUserRepository.updateViewCount(uno);
    }


    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QWebUser qUser = QWebUser.webUser;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qUser.uno.gt(0L);
        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) return booleanBuilder;

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        /**
         * d : d type
         * t : title
         * 한글 제목으로만 검색할 수 있도록 함
         */

        if(type.contains("d")) conditionBuilder.or(qUser.type.contains(keyword));
        if(type.contains("t")) conditionBuilder.or(qUser.title.contains(keyword));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public WebUserDTO get(Long uno) {
        Object res = webUserRepository.getWebUserByUno(uno);
        Object[] arr = (Object[])res;

        return entityToDTO((WebUser)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Override
    @Transactional
    public void removeWithReplies(Long uno) {
        replyRepository.deleteByUno(uno);
        webUserRepository.deleteById(uno);
    }

    @Override
    @Transactional
    public void modify(WebUserDTO webUserDTO) {
        /**
         * 로딩 지연을 위해 getOne 사용
         */
        WebUser webUser = webUserRepository.getOne(webUserDTO.getUno());

        webUser.changeTitle(webUserDTO.getTitle());
        webUser.changeContent(webUserDTO.getContent());
        webUser.changeType(webUserDTO.getType());

        webUserRepository.save(webUser);
    }
}
