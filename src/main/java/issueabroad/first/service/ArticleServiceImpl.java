package issueabroad.first.service;

import issueabroad.first.dto.ArticleDTO;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.entity.article.Article;
import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.ArticleRepository;
import issueabroad.first.repository.MemberRepository;
import issueabroad.first.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository repository;
    private final ReplyRepository replyRepository;


    @Override
    public Long register(ArticleDTO dto) {
        log.info(dto);

        Article article = dtoToEntity(dto);
        repository.save(article);

        return article.getAno();
    }

    @Override
    public PageResultDTO<ArticleDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], ArticleDTO> fn = (en -> entityToDTO((Article)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> res = repository.getArticleWithReplyCount(pageRequestDTO.getPageable(Sort.by("ano").descending()));

        return new PageResultDTO<>(res, fn);
    }

    @Override
    public ArticleDTO get(Long ano) {
        Object res = repository.getArticleByAno((ano));
        Object[] arr = (Object[])res;

        return entityToDTO((Article)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long ano) {
        replyRepository.deleteByAno(ano);
        repository.deleteById(ano);
    }

    @Override
    @Transactional
    public void modify(ArticleDTO articleDTO) {
//        Object tempArticle = repository.getArticleByAno(articleDTO.getAno()); // 로딩 지연이 필요하면 수정하기
//        Article article = (Article)tempArticle;
//        Article article = repository.findById(articleDTO.getAno());
        Article article = repository.getOne(articleDTO.getAno());

        article.changeTitle(articleDTO.getTitle());
        article.changeContent(articleDTO.getContent());

        repository.save(article);
    }


}
