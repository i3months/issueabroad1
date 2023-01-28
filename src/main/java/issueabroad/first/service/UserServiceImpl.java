package issueabroad.first.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.entity.article.QUser;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.UserReplyRepository;
import issueabroad.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserReplyRepository replyRepository;

    @Override
    public Long register(UserDTO dto) {
        log.info(dto);

        User user = dtoToEntity(dto);
        repository.save(user);

        return user.getUno();
    }

    @Override
    public PageResultDTO<UserDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<UserDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCount(
                pageRequestDTO.getPageableMain(Sort.by("uno").descending()));

        return new PageResultDTO<>(res, fn);

    }

    @Override
    public PageResultDTO<UserDTO, Object[]> getListSuggest(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCountType("건의",
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);

    }

    @Override
    public PageResultDTO<UserDTO, Object[]> getListMainSuggest(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCountType("건의",
                pageRequestDTO.getPageableMain(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<UserDTO, Object[]> getListFree(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCountType("자유",
                pageRequestDTO.getPageable(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }

    @Override
    public PageResultDTO<UserDTO, Object[]> getListMainFree(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCountType("자유",
                pageRequestDTO.getPageableMain(Sort.by("uno").descending()));


        return new PageResultDTO<>(res, fn);
    }


    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QUser qUser = QUser.user;

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
    public UserDTO get(Long uno) {
        Object res = repository.getUserByUno(uno);
        Object[] arr = (Object[])res;

        return entityToDTO((User)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Override
    @Transactional
    public void removeWithReplies(Long uno) {
        replyRepository.deleteByUno(uno);
        repository.deleteById(uno);
    }

    @Override
    @Transactional
    public void modify(UserDTO userDTO) {
        /**
         * 로딩 지연을 위해 getOne 사용
         */
        User user = repository.getOne(userDTO.getUno());

        user.changeTitle(userDTO.getTitle());
        user.changeContent(userDTO.getContent());

        repository.save(user);
    }
}
