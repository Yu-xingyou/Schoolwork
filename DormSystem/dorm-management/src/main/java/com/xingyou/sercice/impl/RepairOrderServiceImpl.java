package com.xingyou.sercice.impl;

import com.xingyou.mapper.RepairOrderMapper;
import com.xingyou.pojo.RepairOrder;
import com.xingyou.sercice.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairOrderServiceImpl implements RepairOrderService {
    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Override
    public int add(RepairOrder repairOrder) {
        return repairOrderMapper.add(repairOrder);
    }

    @Override
    public List<RepairOrder> queryByStudentId(String id) {
        return repairOrderMapper.queryByStudentId(id);
    }

    @Override
    public int deleteByRepairOrderId(String repairOrderId) {
        return repairOrderMapper.deleteByRepairOrderId(repairOrderId);
    }

    @Override
    public List<RepairOrder> viewAllRepairOrders() {
        return repairOrderMapper.viewAllRepairOrders();
    }

    @Override
    public RepairOrder queryByFormId(String repairOrderId) {
        return repairOrderMapper.queryByFormId(repairOrderId);
    }

    @Override
    public int updateRepairOrderStatus(String repairOrderId) {
        return repairOrderMapper.updateRepairOrderStatus(repairOrderId);
    }

    @Override
    public List<RepairOrder> viewCompletedRepairOrders() {
        return repairOrderMapper.viewCompletedRepairOrders();
    }

    @Override
    public int deleteCompletedRepairOrders() {
        return repairOrderMapper.deleteCompletedRepairOrders();
    }
}
