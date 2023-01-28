package issueabroad.first.repository;

import issueabroad.first.entity.article.Scrap;
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
class ScrapRepositoryTest {

    @Autowired
    private ScrapRepository scrapRepository;


    @Test
    public void testGetUserWithReplyCountAmerica() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("sno").descending());
        Page<Object[]> res = scrapRepository.getScrapWithReplyCountType("미국", pageable);

        System.out.println("-------------");
        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
        System.out.println("-------------");
    }

    @Test
    public void testGetUserWithReplyCountJapan() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("sno").descending());
        Page<Object[]> res = scrapRepository.getScrapWithReplyCountType("일본" ,pageable);

        System.out.println("-------------");
        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
        System.out.println("-------------");
    }

    @Test
    public void testByNum() {
        Object res = scrapRepository.getUserBySno(1l);
        Object[] arr = (Object[])res;

        System.out.println("-----");
        System.out.println(Arrays.toString(arr));
        System.out.println("-----");
    }


    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 7, Sort.by("sno").descending());
        Page<Object[]> res = scrapRepository.getScrapWithReplyCount(pageable);

        System.out.println("----------");
        res.get().forEach(i -> {
            Object[] arr = ((Object[])i);
            System.out.println(Arrays.toString(arr));
        });
        System.out.println("----------");
    }

    @Test
    public void testWithReply() {
        List<Object[]> res = scrapRepository.getScrapWithReply(1l);

        System.out.println("----------");
        for(Object[] k : res) {
            System.out.println(Arrays.toString(k));
        }
        System.out.println("----------");
    }

    @Test
    @Transactional
    public void readTransactionOne() {
        Optional<Scrap> scrap = scrapRepository.findById(2l);
        System.out.println(scrap.get());
    }


    @Test
    public void insertScrapJapan() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Scrap scrap = Scrap.builder()
                    .originTitle("(일본)원문 제목입니다.." + i)
                    .originContent("(일본)원문 내용입니다.." + i)
                    .title("(일본)번역된 제목입니다.." + i)
                    .content("(일본)번역된 내용입니다.." + i)
                    .type("일본")
                    .webSite("스크랩 해 온 웹사이트 이름입니다.." + i)
                    .viewCount(0l)
                    .url("스크랩 한 게시물로 이동할 수 있는 링크입니다.." + i)
                    .build();

            scrapRepository.save(scrap);
        });
    }


    @Test
    public void insertScrapAmerica() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Scrap scrap = Scrap.builder()
                    .originTitle("(미국)원문 제목입니다.." + i)
                    .originContent("(미국)원문 내용입니다.." + i)
                    .title("(미국)번역된 제목입니다.." + i)
                    .content("(미국)번역된 내용입니다.." + i)
                    .type("미국")
                    .webSite("스크랩 해 온 웹사이트 이름입니다.." + i)
                    .viewCount((long)i)
                    .url("스크랩 한 게시물로 이동할 수 있는 링크입니다.." + i)
                    .build();

            scrapRepository.save(scrap);
        });
    }

}