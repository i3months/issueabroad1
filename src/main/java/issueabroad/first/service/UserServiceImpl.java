package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.UserReplyRepository;
import issueabroad.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Required;
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
    public PageResultDTO<UserDTO, Object[]> getListAll(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], UserDTO> fn = (en -> entityToDTO((User)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getUserWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("uno").descending()));

        return new PageResultDTO<>(res, fn);
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
}
