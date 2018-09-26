package com.lzp.springbootlearn.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @RequestMapping("/adduser")
    public int addUser(@RequestParam("name")String name,@RequestParam("age")String age){
        return userService.addUser(name,age);
    }
    @RequestMapping("/findUser")
    public User findUser(@RequestParam("id") String id){
        return userService.findById(id);
    }
    @RequestMapping("/updataById")
    public String updataById(@RequestParam("id") String id,@RequestParam("name") String name){
        try {
            userService.updateById(id, name);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam("id") String id){
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }
}
