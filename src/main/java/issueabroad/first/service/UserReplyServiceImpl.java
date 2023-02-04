package issueabroad.first.service;

import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.reply.UserReply;
import issueabroad.first.repository.UserReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserReplyServiceImpl implements UserReplyService{

    private final UserReplyRepository userReplyRepository;


    @Override
    public List<UserReplyDTO> getAllReplyByUno(Long uno) {
        Function<Object[], UserReplyDTO> fn = (en -> entityToDTO((UserReply)en[0]));

        List<Object[]> res1 = userReplyRepository.getUserReplyByUno(uno);
        List<UserReplyDTO> res2 = res1.stream().map(fn).collect(Collectors.toList());

        return res2;
    }

    @Override
    public List<UserReplyDTO> getList(Long uno) {
        List<UserReply> res = userReplyRepository.getRepliesByUserOrderByUrno(User.builder().uno(uno).build());

        return res.stream().map(userReply -> entityToDTO(userReply)).collect(Collectors.toList());
    }

    @Override
    public void modify(UserReplyDTO userReplyDTO) {
        UserReply userReply = dtoToEntity(userReplyDTO);

        userReplyRepository.save(userReply);
    }

    @Override
    public void remove(Long uno) {
        userReplyRepository.deleteById(uno);
        return;
    }

    @Override
    public Long register(UserReplyDTO userReplyDTO) {
        UserReply userReply = dtoToEntity(userReplyDTO);

        userReplyRepository.save(userReply);

        return userReply.getUrno();
    }
}
