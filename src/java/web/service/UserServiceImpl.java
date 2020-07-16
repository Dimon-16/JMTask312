package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.DAO.UserDAO;
import web.model.User;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(User user) {userDAO.addUser(user);
    }


    @Override
    public void deleteUser(long id) {userDAO.deleteUser(id);
    }


    @Override
    public void updateUser(long id, User user) {
        userDAO.updateUser(id, user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        list = userDAO.getAllUsers();
        return list;
    }
}
