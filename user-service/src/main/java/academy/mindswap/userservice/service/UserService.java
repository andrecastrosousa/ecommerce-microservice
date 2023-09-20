package academy.mindswap.userservice.service;

import academy.mindswap.userservice.model.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    User get(Long id);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);
}
