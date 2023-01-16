package issueabroad.first.service;

import issueabroad.first.dto.ArticleDTO;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.entity.article.Article;
import issueabroad.first.entity.member.Member;
import issueabroad.first.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ArticleService {

    Long register(ArticleDTO dto);

    PageResultDTO<ArticleDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    PageResultDTO<ArticleDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO);


    ArticleDTO get(Long ano);

    void removeWithReplies(Long ano);

    void modify(ArticleDTO articleDTO);

    default ArticleDTO entityToDTO(Article article, Member member, Long replyCount) {
        ArticleDTO articleDTO = ArticleDTO.builder()
                .ano(article.getAno())
                .title(article.getTitle())
                .content(article.getContent())
                .regDate(article.getRegDate())
                .modDate(article.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return articleDTO;
    }

    default Article dtoToEntity(ArticleDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Article article = Article.builder()
                .ano(dto.getAno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return article;
    }

}
