package issueabroad.first.repository.search;

import issueabroad.first.entity.article.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchScrapRepository {

    Scrap search1();

    Page<Object[]> searchPage(String keyword, Pageable pageable);
}
