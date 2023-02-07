package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.WebUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebUserServiceTest {

    @Autowired
    private WebUserService webUserService;

    @Test
    public void testRegister() {
        WebUserDTO dto = WebUserDTO.builder()
                .title("TestTitle...")
                .content("TestContent...")
                .writerEmail("tempuser100@naver.com")
                .build();

        Long uno = webUserService.register(dto);
    }

    @Test
    public void testGetList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<WebUserDTO, Object[]> res = webUserService.getList(pageRequestDTO);

        int idx = 1;

        System.out.println("---");
        for(WebUserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k); idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testGetOne() {
        Long uno = 2l;

        WebUserDTO webUserDTO = webUserService.get(uno);

        System.out.println("---");
        System.out.println(webUserDTO);
        System.out.println("---");
    }

    @Test
    public void testDeleteWithReplies() {
        Long uno = 1l;
        webUserService.removeWithReplies(uno);
    }

    @Test
    public void testModify() {
        WebUserDTO webUserDTO = WebUserDTO.builder()
                .uno(2l)
                .title("변경된 제목입니다...")
                .content("변경된 내용입니다...")
                .build();

        webUserService.modify(webUserDTO);
    }

    @Test
    public void testPageableMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<WebUserDTO, Object[]> res = webUserService.getListMain(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(WebUserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableSuggestMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<WebUserDTO, Object[]> res = webUserService.getListMainSuggest(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(WebUserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableSuggest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<WebUserDTO, Object[]> res = webUserService.getListSuggest(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(WebUserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableFree() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<WebUserDTO, Object[]> res = webUserService.getListFree(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(WebUserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableMainFree() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<WebUserDTO, Object[]> res = webUserService.getListMainFree(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(WebUserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testViewCount() {

        WebUserDTO dto1 = webUserService.get(310l);
        System.out.println("Before : " + dto1.getViewCount());
        webUserService.updateViewCount(310l);
        dto1 = webUserService.get(310l);
        System.out.println("Afteer : " + dto1.getViewCount());
    }
}