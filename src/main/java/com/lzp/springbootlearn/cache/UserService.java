package com.lzp.springbootlearn.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User findById(String id){
        return userMapper.findById(id);
    }
    public int addUser(String name,String age){
        return userMapper.addUser(name,age);
    }
    public void updateById(String id,String name){
        userMapper.updateById(id,name);
    }
    public void deleteById(String id){
        userMapper.deleteById(id);
    }
}
