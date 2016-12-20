package hse.geo.vespera.web.service;

import hse.geo.vespera.data.domain.User;
import hse.geo.vespera.exception.UserNotFoundException;
import hse.geo.vespera.data.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserWebService implements IUserWebService{

    private final IUserService userService;

    @Autowired
    public UserWebService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @Override
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    @Override
    @RequestMapping(value = "/user/auth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public User findUserByCredentials(@RequestBody UserCredentials credentials) throws UserNotFoundException {
        return userService.findUserByCredentials(credentials.getUserName(), credentials.getPassword());
    }
}
