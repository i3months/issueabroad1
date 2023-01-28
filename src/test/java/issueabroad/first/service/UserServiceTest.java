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

        PageResultDTO<UserDTO, Object[]> res = userService.getListAll(pageRequestDTO);

        System.out.println("---");
        for(UserDTO k : res.getDtoList()) {
            System.out.println(k);
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

}