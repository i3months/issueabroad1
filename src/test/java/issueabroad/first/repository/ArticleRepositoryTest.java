package issueabroad.first.repository;

import issueabroad.first.entity.article.Article;
import issueabroad.first.entity.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void readOneTest() {
        Object res = articleRepository.getArticleByAno(100L);

        Object[] arr = (Object[])res;

        System.out.println("Result is : " + Arrays.toString(arr));
    }

    @Test
    public void readWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("ano").descending());
        Page<Object[]> res = articleRepository.getArticleWithReplyCount(pageable);

        res.get().forEach(row -> {
            Object[] arr = (Object[])row;

            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void readWithReply() {
        List<Object[]> res = articleRepository.getArticleWithReply(100L);

        int idx = 1;

        for(Object[] arr : res) {
            System.out.println(idx + " " + Arrays.toString(arr));
            idx++;
        }

    }

    @Test
    public void readWithWriter() {
        Object result = articleRepository.getArticleWithWriter(3l);

        Object[] arr = (Object[])result;

        System.out.println("-------------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void insertArticle() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("asdf" + i + "@naver.com")
                    .build();

//            String s = "asdf" + i + "@naver.com";
//            Optional<Member> member = memberRepository.findById(s);

//            System.out.println(s);
//            System.out.println("asdfzcxv : " + member.get());

            Article article = Article.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer(member)
                    .build();

            articleRepository.save(article);
        });
    }

}