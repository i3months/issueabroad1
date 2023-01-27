package issueabroad.first.repository;

import issueabroad.first.entity.reply.UserReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReplyRepository extends JpaRepository<UserReply, Long> {
}
