package issueabroad.first.repository.search;

import issueabroad.first.entity.article.Scrap;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class SearchScrapRepositoryImpl extends QuerydslRepositorySupport implements SearchScrapRepository {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public SearchScrapRepositoryImpl() {
        super(Scrap.class);
    }

    @Override
    public Scrap search1() {
        log.info("search1...");

        return null;
    }


}
