package issueabroad.first.service;

import issueabroad.first.dto.UserDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Override
    public Long register(UserDTO dto) {
        log.info(dto);

        User user = dtoToEntity(dto);

        repository.save(user);

        return user.getUno();
    }
}
