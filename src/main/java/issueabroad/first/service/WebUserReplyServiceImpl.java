package issueabroad.first.service;

import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.entity.article.WebUser;
import issueabroad.first.entity.reply.WebUserReply;
import issueabroad.first.repository.WebUserReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class WebUserReplyServiceImpl implements WebUserReplyService {

    private final WebUserReplyRepository webUserReplyRepository;


    @Override
    public List<UserReplyDTO> getAllReplyByUno(Long uno) {
        Function<Object[], UserReplyDTO> fn = (en -> entityToDTO((WebUserReply)en[0]));

        List<Object[]> res1 = webUserReplyRepository.getWebUserReplyByUno(uno);
        List<UserReplyDTO> res2 = res1.stream().map(fn).collect(Collectors.toList());

        return res2;
    }

    @Override
    public List<UserReplyDTO> getList(Long uno) {
        List<WebUserReply> res = webUserReplyRepository.getRepliesByWebUserOrderByUrno(WebUser.builder().uno(uno).build());

        return res.stream().map(userReply -> entityToDTO(userReply)).collect(Collectors.toList());
    }

    @Override
    public void modify(UserReplyDTO userReplyDTO) {
        WebUserReply webUserReply = dtoToEntity(userReplyDTO);

        webUserReplyRepository.save(webUserReply);
    }

    @Override
    public void remove(Long uno) {
        webUserReplyRepository.deleteById(uno);
        return;
    }

    @Override
    public Long register(UserReplyDTO userReplyDTO) {
        WebUserReply webUserReply = dtoToEntity(userReplyDTO);

        webUserReplyRepository.save(webUserReply);

        return webUserReply.getUrno();
    }
}
