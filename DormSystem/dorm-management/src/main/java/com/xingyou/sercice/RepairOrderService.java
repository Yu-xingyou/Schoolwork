package com.xingyou.sercice;

import com.xingyou.pojo.RepairOrder;

import java.util.List;

public interface RepairOrderService {
     int add(RepairOrder repairOrder) ;

    List<RepairOrder> queryByStudentId(String id);

    int deleteByRepairOrderId(String repairOrderId);

    List<RepairOrder> viewAllRepairOrders();

    RepairOrder queryByFormId(String repairOrderId);

    int updateRepairOrderStatus(String repairOrderId);

    List<RepairOrder> viewCompletedRepairOrders();

    int deleteCompletedRepairOrders();
}
