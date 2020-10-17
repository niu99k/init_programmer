package com.gg.test_swagger2.controller;

import com.gg.test_swagger2.domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    static Map<Integer, User> userMap = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> userList() {
        List<User> result = new ArrayList<>();
        result = new ArrayList<>(userMap.values());
        return result;
    }

    @ApiOperation(value = "创建用户", notes = "")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        String result = "suc";
        userMap.put(user.getId(), user);
        return result;
    }

    @ApiOperation(value = "删除用户",notes="根据id删除用户")
    @RequestMapping(value = "/{deleteId}", method = RequestMethod.DELETE)
    public String delUser(@PathVariable int deleteId) {
        String result = "suc";
        userMap.remove(deleteId);
        return result;
    }

    @ApiOperation(value = "查询用户",notes = "根据id查询用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User user(@PathVariable int id) {
        User result = userMap.get(id);
        return result;
    }

    @ApiOperation(value = "修改用户",notes="根据id查询用户")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String updateUser(@ModelAttribute User user) {
        String result = "suc";
        userMap.put(user.getId(), user);
        return result;
    }
}