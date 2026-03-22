package com.xingyou.controller;

import com.xingyou.pojo.RepairOrder;
import com.xingyou.pojo.Student;
import com.xingyou.sercice.RepairOrderService;
import com.xingyou.sercice.StudentService;
import com.xingyou.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * 学生控制器
 * 负责学生相关的所有操作
 */
@Component
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RepairOrderService repairOrderService;

    private Scanner scanner = new Scanner(System.in);

    /**
     * 显示学生菜单
     */
    public void showStudentMenu(Student student) {
        // 首次登录检查宿舍绑定
        if (student.getDormitoryId() == null) {
            System.out.println(" 首次登录，请先绑定宿舍号！");
            bindDormitoryForStudent(student);
            return;
        }

        System.out.println("===== 学生菜单 =====");
        System.out.println("1. 绑定/修改宿舍");
        System.out.println("2. 创建报修单");
        System.out.println("3. 查看我的报修记录");
        System.out.println("4. 取消报修单");
        System.out.println("5. 修改密码");
        System.out.println("6. 退出");
        System.out.print("请选择操作（输入 1-6）：");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                bindDormitoryForStudent(student);
                break;
            case "2":
                createRepairOrder(student);
                break;
            case "3":
                queryMyRepairOrders(student);
                break;
            case "4":
                cancelRepairOrder(student);
                break;
            case "5":
                changePassword(student);
                break;
            case "6":
                System.out.println("退出成功！");
                System.exit(0);
            default:
                System.out.println("输入错误，请重新输入！");
        }
    }

    private void changePassword(Student student) {
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
        student.setPassword(encodedPassword);
        
        int result = studentService.update(student);
        if (result > 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败，请重试！");
        }
    }

    private void cancelRepairOrder(Student student) {
        queryMyRepairOrders(student);
        System.out.println("请输入要取消的报修单编号：");
        String repairOrderId = scanner.nextLine();
        int result = repairOrderService.deleteByRepairOrderId(repairOrderId);
        if (result > 0) {
            System.out.println("取消成功！");
        }
        else {
            System.out.println("取消失败！请检查是否输入了正确的保修单编号！");
        }
    }

    /**
     * 绑定或修改宿舍号
     */
    private void bindDormitoryForStudent(Student student) {
        System.out.println("===== 绑定宿舍号 =====");
        System.out.println("请输入宿舍号：");
        String dormitoryId = scanner.nextLine();

        student.setDormitoryId(dormitoryId);
        student.setUpdateTime(LocalDateTime.now());

        int result = studentService.update(student);
        if (result > 0) {
            System.out.println("绑定成功！");
        } else {
            System.out.println("绑定失败！");
        }
    }

    /**
     * 创建报修单
     */
    private void createRepairOrder(Student student) {
        System.out.println("请选择报修类型：");
        System.out.println("1.水龙头");
        System.out.println("2.电器");
        System.out.println("3.桌椅");
        System.out.println("4.其他");
        System.out.print("请选择报修类型（输入 1-4）：");

        String repairType = scanner.nextLine();
        RepairOrder repairOrder = new RepairOrder();

        switch (repairType) {
            case "1":
                repairOrder.setDamageType("水龙头");
                break;
            case "2":
                repairOrder.setDamageType("电器");
                break;
            case "3":
                repairOrder.setDamageType("桌椅");
                break;
            case "4":
                repairOrder.setDamageType("其他");
                break;
            default:
                System.out.println("输入错误，请重新输入！");
                return;
        }

        System.out.println("请输入问题描述：");
        String damageDesc = scanner.nextLine();

        repairOrder.setStudentId(student.getId());
        repairOrder.setDormitoryId(student.getDormitoryId());
        repairOrder.setDamageDesc(damageDesc);
        repairOrder.setStatus("待处理");
        repairOrder.setCreateTime(LocalDateTime.now());
        repairOrder.setUpdateTime(LocalDateTime.now());

        int result = repairOrderService.add(repairOrder);
        if (result > 0) {
            System.out.println("报修成功！后续将为您修理！");
        } else {
            System.out.println("报修失败！");
        }
    }

    /**
     * 查询学生的报修记录
     */
    private void queryMyRepairOrders(Student student) {
        List<RepairOrder> repairOrders = repairOrderService.queryByStudentId(student.getId());

        if (repairOrders.isEmpty()) {
            System.out.println("你还没有报修记录！");
            return;
        }

        System.out.println("\n===== 我的报修记录 =====");
        for (RepairOrder order : repairOrders) {
            System.out.println("报修单编号：" + order.getId());
            System.out.println("报修类型：" + order.getDamageType());
            System.out.println("问题描述：" + order.getDamageDesc());
            System.out.println("报修状态：" + order.getStatus());
            System.out.println("创建时间：" + order.getCreateTime());
            System.out.println("------------------------");
        }
    }
}

