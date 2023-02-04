package issueabroad.first.repository;

import issueabroad.first.entity.article.User;
import issueabroad.first.entity.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testGetUserWithReplyCountSuggest() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("uno").descending());
        Page<Object[]> res = userRepository.getUserWithReplyCountType("건의" ,pageable);

        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testByNum() {
        Object res = userRepository.getUserByUno(2l);
        Object[] arr = (Object[])res;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("uno").descending());
        Page<Object[]> res = userRepository.getUserWithReplyCount(pageable);

        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testWithReply() {
        List<Object[]> res = userRepository.getUserWithReply(198l);

        for(Object[] k : res) {
            System.out.println(Arrays.toString(k));
        }
    }

    @Test
    public void testWithWriter() {
        Object obj = userRepository.getUserWithWriter(2l);
        Object[] arr = (Object[])obj;

        System.out.println("---");
        System.out.println(Arrays.toString(arr));
        System.out.println("---");
    }

    @Test
    @Transactional
    public void readTransactionOne() {
        Optional<User> user = userRepository.findById(2l);
        System.out.println(user.get());
        System.out.println(user.get().getWriter());
    }

    @Test
    public void insertUserBoardSuggest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .build();

            User user = User.builder()
                    .title("suggest title..." + i)
                    .content("suggest content..." + i)
                    .writer(member)
                    .viewCount(0l)
                    .type("건의")
                    .build();

            userRepository.save(user);

        });
    }

    @Test
    public void insertUserBoardFree() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .build();

            User user = User.builder()
                    .title("free title..." + i)
                    .content("free content..." + i)
                    .writer(member)
                    .viewCount(0l)
                    .type("자유")
                    .build();

            userRepository.save(user);

        });
    }

    @Test
    public void insertUserBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("tempuser" + i + "@naver.com")
                    .build();

            User user = User.builder()
                    .title("TempTitle..." + i)
                    .content("TempContent..." + i)
                    .writer(member)
                    .build();

            userRepository.save(user);

        });
    }





}