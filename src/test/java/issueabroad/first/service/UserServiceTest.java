package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        UserDTO dto = UserDTO.builder()
                .title("TestTitle...")
                .content("TestContent...")
                .writerEmail("tempuser100@naver.com")
                .build();

        Long uno = userService.register(dto);
    }

    @Test
    public void testGetList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<UserDTO, Object[]> res = userService.getList(pageRequestDTO);

        int idx = 1;

        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " + k); idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testGetOne() {
        Long uno = 2l;

        UserDTO userDTO = userService.get(uno);

        System.out.println("---");
        System.out.println(userDTO);
        System.out.println("---");
    }

    @Test
    public void testDeleteWithReplies() {
        Long uno = 1l;
        userService.removeWithReplies(uno);
    }

    @Test
    public void testModify() {
        UserDTO userDTO = UserDTO.builder()
                .uno(2l)
                .title("변경된 제목입니다...")
                .content("변경된 내용입니다...")
                .build();

        userService.modify(userDTO);
    }

    @Test
    public void testPageableMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<UserDTO, Object[]> res = userService.getListMain(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableSuggestMain() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<UserDTO, Object[]> res = userService.getListMainSuggest(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableSuggest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<UserDTO, Object[]> res = userService.getListSuggest(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableFree() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<UserDTO, Object[]> res = userService.getListFree(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }

    @Test
    public void testPageableMainFree() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<UserDTO, Object[]> res = userService.getListMainFree(pageRequestDTO);

        int idx = 1;
        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(idx + " : " +k);
            idx++;
        }
        System.out.println("---");
    }
}