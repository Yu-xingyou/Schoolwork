package com.xingyou.sercice;

import com.xingyou.pojo.Admin;

public interface AdminService {
    Admin login(String id, String password);

    Admin findById(String id);

    int register(String id, String password);

    int update(Admin admin);
}
