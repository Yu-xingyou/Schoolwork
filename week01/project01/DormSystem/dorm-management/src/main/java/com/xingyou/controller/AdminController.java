package com.xingyou.controller;

import com.xingyou.pojo.Admin;
import com.xingyou.pojo.RepairOrder;
import com.xingyou.sercice.AdminService;
import com.xingyou.sercice.RepairOrderService;
import com.xingyou.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * 管理员控制器
 * 负责管理员相关的所有操作
 */
@Component
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RepairOrderService repairOrderService;

    private Scanner scanner = new Scanner(System.in);

    /**
     * 显示管理员菜单
     */
    public void showAdminMenu(Admin admin) {
        System.out.println("\n===== 管理员菜单 =====");
        System.out.println("1. 查看所有报修单");
        System.out.println("2. 查看报修单详情");
        System.out.println("3. 更新报修单状态");
        System.out.println("4. 删除报修单");
        System.out.println("5. 修改密码");
        System.out.println("6. 查看所有已完成保修单（进入页面后可选择是否快速删除所有已完成保修单）");
        System.out.println("7. 退出");
        System.out.print("请选择操作（输入 1-6）：");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                viewAllRepairOrders();
                break;
            case "2":
                viewRepairOrderDetails();
                break;
            case "3":
                updateRepairOrderStatus();
                break;
            case "4":
                deleteRepairOrder();;
                break;
            case "5":
                changePassword(admin);
                break;
            case "6":
                CompletedRepairOrders();
                break;
            case "7":
                System.out.println("退出成功！");
                System.exit(0);
            default:
                System.out.println("输入错误，请重新输入！");
        }
    }

    private void CompletedRepairOrders() {
        List<RepairOrder> completedRepairOrders = repairOrderService.viewCompletedRepairOrders();
        if (completedRepairOrders.isEmpty()) {
            System.out.println("没有已完成保修单！");
            return;
        }
        System.out.println("====以下为已完成保修单=====");
        for (RepairOrder repairOrder : completedRepairOrders) {
            System.out.println("报修单编号：" + repairOrder.getId());
            System.out.println("报修宿舍号：" + repairOrder.getDormitoryId());
            System.out.println("报修类型：" + repairOrder.getDamageType());
            System.out.println("报修描述：" + repairOrder.getDamageDesc());
            System.out.println("报修状态：" + repairOrder.getStatus());
        }
        System.out.println("是否快速删除所有已完成保修单？");
        System.out.print("请输入1（删除）");
        System.out.print("请输入2（返回）");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            int result = repairOrderService.deleteCompletedRepairOrders();
            if (result > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        }
        else if (choice.equals("2")) {
            return;
        }
    }

    private void changePassword(Admin admin) {
        System.out.println("====请输入新密码====");
        String newPassword = scanner.nextLine();
        System.out.println("====确认密码=====");
        String confirmPassword = scanner.nextLine();
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("两次输入的密码不一致，请重新修改！");
            return;
        }
        
        // 加密新密码
        String encodedPassword = PasswordUtil.encode(newPassword);
        admin.setPassword(encodedPassword);
        
        int result = adminService.update(admin);
        if (result > 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败，请重试！");
        }
    }

    private void deleteRepairOrder() {
        System.out.println("请输入要删除的报修单编号：");
        String repairOrderId = scanner.nextLine();
        int result = repairOrderService.deleteByRepairOrderId(repairOrderId);
        if (result > 0) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败！");
        }
    }

    private void updateRepairOrderStatus() {
        System.out.println("请输入要更新的报修单编号：");
        String repairOrderId = scanner.nextLine();
        int result =repairOrderService.updateRepairOrderStatus(repairOrderId);
        if (result > 0) {
            System.out.println("更新成功！");
        } else {
            System.out.println("更新失败！");
        }
    }

    private void viewRepairOrderDetails() {
        System.out.println("请输入要查看的报修单编号：");
        String repairOrderId = scanner.nextLine();
        RepairOrder repairOrder = repairOrderService.queryByFormId(repairOrderId);
        if (repairOrder == null) {
            System.out.println("没有该报修单！");
            return;
        }
        System.out.println("\n===== 报修单详情 =====");
        System.out.println("报修单编号：" + repairOrder.getId());
        System.out.println("报修宿舍号：" + repairOrder.getDormitoryId());
        System.out.println("报修类型：" + repairOrder.getDamageType());
        System.out.println("报修描述：" + repairOrder.getDamageDesc());
        System.out.println("报修状态：" + repairOrder.getStatus());
        System.out.println("创建时间：" + repairOrder.getCreateTime());
        System.out.println("更新时间：" + repairOrder.getUpdateTime());
    }

    private void viewAllRepairOrders() {
        System.out.println("=====所有保修单====");
        //按时间顺序先后排序
        List<RepairOrder> allRepairOrders = repairOrderService.viewAllRepairOrders();

        if (allRepairOrders.isEmpty()) {
            System.out.println("还没有报修记录！");
            return;
        }

        System.out.println("\n===== 所有报修记录 =====");
        for (RepairOrder order : allRepairOrders) {
            System.out.println("报修单编号：" + order.getId());
            System.out.println("报修宿舍号"+order.getDormitoryId());
            System.out.println("报修类型：" + order.getDamageType());
            System.out.println("报修状态：" + order.getStatus());
            System.out.println("创建时间：" + order.getCreateTime());
            System.out.println("------------------------");
        }
    }
}


