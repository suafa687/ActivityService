package org.example.activitytest.system.controller;

import org.example.activitytest.base.BaseController;
import org.example.activitytest.base.Result;
import org.example.activitytest.constants.BaseEnums;
import org.example.activitytest.constants.Constants;
import org.example.activitytest.system.dto.User;
import org.example.activitytest.util.Dates;
import org.example.activitytest.util.Results;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户Controller
 *
 * @author bojiangzhou 2017-12-31
 * @version 1.0
 */
@RequestMapping("/")
@RestController
public class UserController extends BaseController {

    private static List<User> userList = new ArrayList<>();

    // 先静态模拟数据
    static {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("lufei");
        user1.setNickname("蒙奇D路飞");
        user1.setBirthday(Dates.parseDate("2000-05-05"));
        user1.setSex(Constants.Sex.MALE);
        user1.setEnabled(Constants.Flag.YES);
        userList.add(user1);

        User user2 = new User();
        user2.setUserId(2L);
        user2.setUsername("nami");
        user2.setNickname("娜美");
        user2.setBirthday(Dates.parseDate("2000/7/3"));
        user2.setSex(Constants.Sex.FEMALE);
        user2.setEnabled(Constants.Flag.YES);
        userList.add(user2);
    }

    @RequestMapping(value = "/queryAll", produces = "application/xml")
    public Result queryAll() {
        return Results.successWithData(userList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @RequestMapping(value = "/queryOne/{userId}", produces = "application/xml")
    public Result queryOne(@PathVariable Long userId) {
        User user = null;
        for (User u : userList) {
            if (u.getUserId().longValue() == userId) {
                user = u;
            }
        }
        return Results.successWithData(user);
    }

    @RequestMapping(value = "/queryAllJson", produces = "application/json")
    public List<User> queryAllJson() {
        return userList;
    }
    @RequestMapping(value = "/queryOneJson/{userId}", produces = "application/json")
    public User queryOneJson(@PathVariable Long userId) {
        User user = null;
        for (User u : userList) {
            if (u.getUserId().longValue() == userId) {
                user = u;
            }
        }
        return user;
    }

    @RequestMapping(value = "/queryOneJsonWithPar", produces = "application/json")
    public User queryOneJsonWithPar(@RequestParam("id") Long userId) {
        User user = null;
        for (User u : userList) {
            if (u.getUserId().longValue() == userId) {
                user = u;
            }
        }
        return user;
    }

    @PostMapping(value = "/insertData", produces = "application/json")
    public String insertData(@RequestBody User user) {
        if (user != null) {
            userList.add(user);
            return "success";
        }
        return "false";
    }
}