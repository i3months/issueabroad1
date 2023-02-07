package issueabroad.first.repository;

import issueabroad.first.entity.article.WebUser;
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
class WebUserRepositoryTest {

    @Autowired
    private WebUserRepository webUserRepository;


    @Test
    public void testGetUserWithReplyCountSuggest() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("uno").descending());
        Page<Object[]> res = webUserRepository.getWebUserWithReplyCountType("건의" ,pageable);

        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testByNum() {
        Object res = webUserRepository.getWebUserByUno(2l);
        Object[] arr = (Object[])res;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("uno").descending());
        Page<Object[]> res = webUserRepository.getWebUserWithReplyCount(pageable);

        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testWithReply() {
        List<Object[]> res = webUserRepository.getWebUserWithReply(198l);

        for(Object[] k : res) {
            System.out.println(Arrays.toString(k));
        }
    }

    @Test
    public void testWithWriter() {
        Object obj = webUserRepository.getWebUserWithWriter(2l);
        Object[] arr = (Object[])obj;

        System.out.println("---");
        System.out.println(Arrays.toString(arr));
        System.out.println("---");
    }

    @Test
    @Transactional
    public void readTransactionOne() {
        Optional<WebUser> user = webUserRepository.findById(2l);
        System.out.println(user.get());
        System.out.println(user.get().getWriter());
    }

    @Test
    public void insertWebUserBoardSuggest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .build();

            WebUser webUser = WebUser.builder()
                    .title("suggest title..." + i)
                    .content("suggest content..." + i)
                    .writer(member)
                    .viewCount(0l)
                    .type("건의")
                    .build();

            webUserRepository.save(webUser);

        });
    }

    @Test
    public void insertWebUserBoardFree() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .build();

            WebUser webUser = WebUser.builder()
                    .title("free title..." + i)
                    .content("free content..." + i)
                    .writer(member)
                    .viewCount(0l)
                    .type("자유")
                    .build();

            webUserRepository.save(webUser);

        });
    }

    @Test
    public void insertUserBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("tempuser" + i + "@naver.com")
                    .build();

            WebUser webUser = WebUser.builder()
                    .title("TempTitle..." + i)
                    .content("TempContent..." + i)
                    .writer(member)
                    .build();

            webUserRepository.save(webUser);

        });
    }





}