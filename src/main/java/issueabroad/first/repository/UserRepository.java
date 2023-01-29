package issueabroad.first.repository;

import issueabroad.first.entity.article.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u, w from User u left join u.writer w where u.uno = :uno")
    Object getUserWithWriter(@Param("uno") Long uno);

    @Query("select u, ur from User u left join UserReply ur on ur.user = u where u.uno = :uno")
    List<Object[]> getUserWithReply(@Param("uno") Long uno);


    /**
     * group by 로 게시물 하나당 한 줄로 처리
     */
    @Query(value = "select u, w, count(ur) " +
            " from User u " +
            " left join u.writer w " +
            " left join UserReply ur on ur.user = u " +
            " group by u",
            countQuery = "select count(u) from User u")
    Page<Object[]> getUserWithReplyCount(Pageable pageable);

    @Query(value = "select u, w, count(ur) " +
            " from User u " +
            " left join u.writer w " +
            " left join UserReply ur on ur.user = u " +
            " where u.type = :type " +
            " group by u",
            countQuery = "select count(u) from User u where u.type = :type")
    Page<Object[]> getUserWithReplyCountType(@Param("type") String type, Pageable pageable);


    @Query("select u, w, count(ur) " +
            " from User u left join u.writer w " +
            " left outer join UserReply ur on ur.user = u " +
            " where u.uno = :uno")
    Object getUserByUno(@Param("uno") Long uno);


}
