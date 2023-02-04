package issueabroad.first.repository;

import issueabroad.first.entity.reply.ScrapReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapReplyRepository extends JpaRepository<ScrapReply, Long> {

    @Query("select sr from ScrapReply as sr where sr.scrap.sno = :sno")
    List<Object[]> getUserReplyByUno(Long sno);

}
