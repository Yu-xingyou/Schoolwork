package com.xingyou.mapper;

import com.xingyou.pojo.Admin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminMapper {
    @Select("select admin_id as id, password, admin_name as name, create_time as createTime, update_time as updateTime from admin where admin_id=#{id} and password=#{password}")
    Admin login(String id, String password);

    @Select("select admin_id as id, password, admin_name as name, create_time as createTime, update_time as updateTime from admin where admin_id= #{id}")
    Admin findById(String id);

    @Insert("insert into admin(admin_id,password) values(#{id},#{password})")
    int register(String id, String password);

    @Update("update admin set password=#{password} where admin_id=#{id}")
    int update(Admin admin);
}

