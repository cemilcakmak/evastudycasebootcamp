package com.cemil.evastudycase.controller;

import com.cemil.evastudycase.model.Portfolio;
import com.cemil.evastudycase.model.User;
import com.cemil.evastudycase.service.PortfolioService;
import com.cemil.evastudycase.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final static Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final PortfolioService portfolioService;

    public UserController(UserService userService, PortfolioService portfolioService) {
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws Exception {
        User newlyCreatedUser = userService.saveUser(user);

        Portfolio portfolio = newlyCreatedUser.getPortfolio();
        portfolio.setUserId(newlyCreatedUser.getId());
        portfolio.setTotalWallet(newlyCreatedUser.getWalletAmount());
        portfolio.setCurrentWallet(newlyCreatedUser.getWalletAmount());
        portfolioService.savePortfolio(portfolio);

        return newlyCreatedUser;
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        List<User> newlyCreatedUsers = userService.saveUsers(users);

        for(User eachUser : newlyCreatedUsers) {
           Portfolio portfolio = eachUser.getPortfolio();
           portfolio.setUserId(eachUser.getId());
           portfolio.setTotalWallet(eachUser.getWalletAmount());
           portfolio.setCurrentWallet(eachUser.getWalletAmount());
           portfolioService.savePortfolio(portfolio);
        }
        return newlyCreatedUsers;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.deleteUserById(id);
    }

}
