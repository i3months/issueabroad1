package issueabroad.first.repository;

import issueabroad.first.entity.article.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    @Query("select s, sr from Scrap s left join ScrapReply sr on sr.scrap = s where s.sno = :sno")
    List<Object[]> getScrapWithReply(@Param("sno") Long sno);

    @Query(value = "select s, count(sr) " +
            " from Scrap s " +
            " left join ScrapReply sr on sr.scrap = s " +
            " group by s",
            countQuery = "select count(s) from Scrap s")
    Page<Object[]> getScrapWithReplyCount(Pageable pageable);
}
