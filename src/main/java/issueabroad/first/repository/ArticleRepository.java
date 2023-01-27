//package issueabroad.first.repository;
//
//import issueabroad.first.entity.article.Article;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ArticleRepository extends JpaRepository<Article, Long> {
//
//    @Query("select a, w from Article a left join a.writer w where a.ano = :ano")
//    Object getArticleWithWriter(@Param("ano") Long ano);
//
//    @Query("select a, r from Article a left join UserReply r on r.article = a where a.ano = :ano")
//    List<Object[]> getArticleWithReply(@Param("ano") Long ano);
//
//    @Query(value = "select a, w, count(r) " +
//            " from Article a " +
//            " left join a.writer w " +
//            " left join UserReply r on r.article = a " +
//            " group by a",
//            countQuery = "select count(a) from Article a")
//    Page<Object[]> getArticleWithReplyCount(Pageable pageable);
//
//    @Query("select a, w, count(r) " +
//            " from Article a left join a.writer w " +
//            " left outer join UserReply r on r.article = a " +
//            " where a.ano = :ano")
//    Object getArticleByAno(@Param("ano") Long ano);
//}
