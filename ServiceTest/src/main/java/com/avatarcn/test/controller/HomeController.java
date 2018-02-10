package com.avatarcn.test.controller;

import com.avatarcn.test.exception.ErrorCode;
import com.avatarcn.test.json.JsonBean;
import com.avatarcn.test.model.User;
import com.avatarcn.test.json.response.AllResponse;
import com.avatarcn.test.json.response.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by z1ven on 2017/11/30 13:57
 */
@Api(value = "/home", description = "测试接口")
@RequestMapping(value = "/home")
@RestController
public class HomeController {

    @Autowired
    private DiscoveryClient client;

    @ApiOperation("获取请求端口信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<String> serviceIndex() {
        return new JsonBean<>(ErrorCode.SUCCESS, "cloud-service's port :" + client.getLocalServiceInstance().getPort());
    }

    @ApiOperation("获取指定的用户")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<User> one(@ApiParam(value = "用户名", required = true) @RequestParam(value = "name") String name) {
        Random random = new Random();
        User user = new User();
        user.setName(name);
        user.setId((random.nextLong()));
        user.setAddress("HeFei");
        return new JsonBean<>(ErrorCode.SUCCESS, user);
    }

    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<AllResponse<User>> two(@ApiParam(value = "arg1", required = true) @RequestParam(value = "arg1") String arg1,
                                           @ApiParam(value = "arg2", required = true) @RequestParam(value = "arg2") String arg2) {
        Random random = new Random();
        User user = new User();
        user.setName(arg1);
        user.setId(random.nextLong());
        user.setAddress("HeFei");
        User user1 = new User();
        user1.setName(arg2);
        user1.setId(random.nextLong());
        user1.setAddress("HeFei");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        AllResponse<User> allResponse = new AllResponse<>();
        allResponse.setItems(users);
        allResponse.setTotal(users.size());
        return new JsonBean<>(ErrorCode.SUCCESS, allResponse);
    }

    @ApiOperation("分页获取所有用户")
    @RequestMapping(value = "/user/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<User>> three(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                              @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Random random = new Random();
        List<User> userList = new ArrayList<>();
        for (int i=0; i < pageSize; i++) {
            User user = new User();
            user.setName("用户" + i);
            user.setId(random.nextLong());
            userList.add(user);
        }
        PageResponse<User> pageResponse = new PageResponse<>();
        pageResponse.setItems(userList.subList(offset, offset + pageSize));
        pageResponse.setOffset(offset);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(userList.size());
        return new JsonBean<>(ErrorCode.SUCCESS, pageResponse);
    }

}
