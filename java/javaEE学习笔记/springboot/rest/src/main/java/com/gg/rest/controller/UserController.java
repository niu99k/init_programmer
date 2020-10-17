package com.gg.rest.controller;

import com.gg.rest.domain.User;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{deleteId}", method = RequestMethod.DELETE)
    public String delUser(@PathVariable int deleteId) {
        String result = "suc";
        userMap.remove(deleteId);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User user(@PathVariable int id) {
        User result = userMap.get(id);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String updateUser(@ModelAttribute User user) {
        String result = "suc";
        userMap.put(user.getId(), user);
        return result;
    }
}