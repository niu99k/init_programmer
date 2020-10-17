package com.gg.rest.controller;

import com.gg.rest.domain.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    static Map<Integer, User> userMap = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> userList() {
        List<User> result = new ArrayList<>();
        result = new ArrayList<>(userMap.values());
        return result;
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        String result = "suc";
        userMap.put(user.getId(), user);
        return result;
    }
}
