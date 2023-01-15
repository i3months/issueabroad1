package issueabroad.first.repository;

import issueabroad.first.entity.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying // JPQL로 u d 를 실행하기 위한 애너테이션
    @Query("delete from Reply r where r.article.ano = :ano")
    void deleteByAno(Long ano);
}
