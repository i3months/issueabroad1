package issueabroad.first.service;

import issueabroad.first.dto.ScrapReplyDTO;
import issueabroad.first.entity.reply.ScrapReply;
import issueabroad.first.repository.ScrapReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScrapReplyServiceImpl implements ScrapReplyService {

    private final ScrapReplyRepository scrapReplyRepository;


    @Override
    public List<ScrapReplyDTO> getAllReplyBySno(Long sno) {
        Function<Object[], ScrapReplyDTO> fn = (en -> entityToDTO((ScrapReply)en[0]));

        List<Object[]> res1 = scrapReplyRepository.getUserReplyByUno(sno);
        List<ScrapReplyDTO> res2 = res1.stream().map(fn).collect(Collectors.toList());

        return res2;


    }
}
