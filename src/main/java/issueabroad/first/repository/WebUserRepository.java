package issueabroad.first.repository;

import issueabroad.first.entity.article.WebUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    @Query("select u, w from WebUser u left join u.writer w where u.uno = :uno")
    Object getWebUserWithWriter(@Param("uno") Long uno);

    @Query("select u, ur from WebUser u left join WebUserReply ur on ur.webUser = u where u.uno = :uno")
    List<Object[]> getWebUserWithReply(@Param("uno") Long uno);


    /**
     * group by 로 게시물 하나당 한 줄로 처리
     */
    @Query(value = "select u, w, count(ur) " +
            " from WebUser u " +
            " left join u.writer w " +
            " left join WebUserReply ur on ur.webUser = u " +
            " group by u",
            countQuery = "select count(u) from WebUser u")
    Page<Object[]> getWebUserWithReplyCount(Pageable pageable);

    @Query(value = "select u, w, count(ur) " +
            " from WebUser u " +
            " left join u.writer w " +
            " left join WebUserReply ur on ur.webUser = u " +
            " where u.type = :type " +
            " group by u",
            countQuery = "select count(u) from WebUser u where u.type = :type")
    Page<Object[]> getWebUserWithReplyCountType(@Param("type") String type, Pageable pageable);


    @Query("select u, w, count(ur) " +
            " from WebUser u left join u.writer w " +
            " left outer join WebUserReply ur on ur.webUser = u " +
            " where u.uno = :uno")
    Object getWebUserByUno(@Param("uno") Long uno);


    @Modifying
    @Query("update WebUser u set u.viewCount = u.viewCount + 1 where u.uno = :uno")
    int updateViewCount(Long uno);

}
