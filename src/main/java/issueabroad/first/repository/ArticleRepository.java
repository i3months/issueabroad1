package issueabroad.first.repository;

import issueabroad.first.entity.article.Article2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article2, Long> {
}
