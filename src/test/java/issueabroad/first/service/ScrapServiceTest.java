package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.ScrapDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScrapServiceTest {

    @Autowired
    private ScrapService scrapService;

    @Test
    public void testGetOne() {
        Long sno = 1l;
        ScrapDTO scrapDTO = scrapService.get(sno);

        System.out.println("-----------");
        System.out.println(scrapDTO);
        System.out.println("-----------");
    }

    @Test
    public void testPageableAll() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getList(pageRequestDTO);

        int idx = 1;

        System.out.println("---------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("---------");
    }

    @Test
    public void testPageableMainAll() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListMain(pageRequestDTO);

        int idx = 1;

        System.out.println("---------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("---------");
    }

    @Test
    public void testPageableAmerica() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListAmerica(pageRequestDTO);

        int idx = 1;

        System.out.println("---------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("---------");
        System.out.println(res.getTotalPage());

    }

    @Test
    public void testPageableAmericaMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListMainAmerica(pageRequestDTO);

        int idx = 1;

        System.out.println("---------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("---------");
    }

    @Test
    public void testPageableJapan() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListJapan(pageRequestDTO);

        int idx = 1;

        System.out.println("---------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("---------");
    }

    @Test
    public void testPageableJapanMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListMainJapan(pageRequestDTO);

        int idx = 1;

        System.out.println("---------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("---------");
    }

    @Test
    public void testViewCount() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListViewCount(pageRequestDTO);

        int idx = 1;

        System.out.println("----------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("----------");
    }

    @Test
    public void testViewCountMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<ScrapDTO, Object[]> res = scrapService.getListViewCountMain(pageRequestDTO);

        int idx = 1;

        System.out.println("----------");
        for(ScrapDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k);
            idx++;
        }
        System.out.println("----------");
    }

}