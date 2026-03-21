package com.xingyou.mapper;

import com.xingyou.pojo.RepairOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RepairOrderMapper {
    @Select("SELECT form_id as id, student_id as studentId, dormitory_id as dormitoryId, damage_type as damageType, damage_desc as damageDesc, status, create_time as createTime, update_time as updateTime FROM repair_order WHERE status = '已完成'")
    List<RepairOrder> viewCompletedRepairOrders() ;

    @Update("UPDATE repair_order SET status = '已处理' WHERE form_id = #{repairOrderId}")
    int updateRepairOrderStatus(String repairOrderId);

    @Insert("INSERT INTO repair_order(student_id, dormitory_id, damage_type, damage_desc, status) VALUES(#{studentId}, #{dormitoryId}, #{damageType}, #{damageDesc}, #{status})")
    int add(RepairOrder repairOrder);

    @Select("SELECT form_id as id, student_id as studentId, dormitory_id as dormitoryId, damage_type as damageType, damage_desc as damageDesc, status, create_time as createTime, update_time as updateTime FROM repair_order WHERE student_id = #{id}")
    List<RepairOrder> queryByStudentId(String id);

    @Delete("DELETE FROM repair_order WHERE form_id = #{repairOrderId}")
    int deleteByRepairOrderId(String repairOrderId);

    @Select("SELECT form_id as id, student_id as studentId, dormitory_id as dormitoryId, damage_type as damageType, damage_desc as damageDesc, status, create_time as createTime, update_time as updateTime FROM repair_order ORDER BY create_time")
    List<RepairOrder> viewAllRepairOrders();

    @Select("SELECT form_id as id, student_id as studentId, dormitory_id as dormitoryId, damage_type as damageType, damage_desc as damageDesc, status, create_time as createTime, update_time as updateTime FROM repair_order WHERE form_id = #{repairOrderId}")
    RepairOrder queryByFormId(String repairOrderId);


    @Delete("DELETE FROM repair_order WHERE status = '已完成'")
    int deleteCompletedRepairOrders();
}
