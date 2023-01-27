package issueabroad.first.service;

import issueabroad.first.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}