package com.xingyou.sercice.impl;

import com.xingyou.mapper.AdminMapper;
import com.xingyou.pojo.Admin;
import com.xingyou.sercice.AdminService;
import com.xingyou.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String id, String password) {
        Admin admin = adminMapper.findById(id);
        if (admin != null && PasswordUtil.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }

    @Override
    public Admin findById(String id) {
        return adminMapper.findById(id);
    }

    @Override
    public int register(String id, String password) {
        String encodedPassword = PasswordUtil.encode(password);
        return adminMapper.register(id, encodedPassword);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }
}
