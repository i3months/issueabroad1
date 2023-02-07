package issueabroad.first.repository;

import issueabroad.first.entity.article.WebUser;
import issueabroad.first.entity.reply.WebUserReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebUserReplyRepository extends JpaRepository<WebUserReply, Long> {


    /**
     * 특정 게시물 번호로 게시글에 달린 댓글을 삭제하는 메서드
     * 게시글을 삭제할 때는 게시글에 달린 댓글을 지우고 게시글을 삭제하는 순서로 진행됨
     */
    @Modifying
    @Query("delete from WebUserReply ur where ur.webUser.uno = :uno")
    void deleteByUno(Long uno);

    @Query("select ur from WebUserReply as ur where ur.webUser.uno = :uno")
    List<Object[]> getWebUserReplyByUno(Long uno);

    List<WebUserReply> getRepliesByWebUserOrderByUrno(WebUser webUser);


}
