package com.xingyou.controller;

import com.xingyou.pojo.Admin;
import com.xingyou.pojo.Student;
import com.xingyou.sercice.AdminService;
import com.xingyou.sercice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.Scanner;

/**
 * 主控制器
 * 负责主菜单、用户注册和登录
 */
@RestController
public class Controller {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentController studentController;

    @Autowired
    private AdminController adminController;

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while ( true) {
            showMainMenu();
        }
    }

    private void showMainMenu() {
        System.out.println("===========================");
        System.out.println("\uD83C\uDFE0 宿舍报修管理系统");
        System.out.println("===========================");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出");
        System.out.print("请选择操作（输入 1-3）：");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                showLoginMenu();
                return;
            case "2":
                showRegisterMenu();
                return;
            case "3":
                System.out.println("感谢使用宿舍报修管理系统！再见！");
                System.exit(0);
                return;
            default:
                System.out.println("输入错误，请重新输入！");
                break;
        }
    }

    private void showRegisterMenu() {
        System.out.println("===== 用户注册 =====");
        System.out.println("请选择角色（1-学生，2-维修人员）：");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                registerStudent();
                break;
            case "2":
                registerAdmin();
                break;
            default:
                System.out.println("输入错误，请重新输入！");
        }
    }

    private void registerAdmin() {
        System.out.println("请输入工号（前缀为“0025”）：");
        String id = scanner.nextLine();

        if (!id.startsWith("0025")) {
            System.out.println("工号信息错误，请重新输入！");
            return;
        }

        Admin admin = adminService.findById(id);
        if (admin != null) {
            System.out.println("该工号已存在，请勿重复注册！");
            return;
        }

        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        System.out.println("请确认密码：");
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("密码不一致，请重新输入！");
            return;
        }

        int result = adminService.register(id, password);
        if (result > 0) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }

    private void registerStudent() {
        System.out.println("请输入学号（前缀 3125 或 3225）：");
        String id = scanner.nextLine();

        if (!id.startsWith("3125") && !id.startsWith("3225")) {
            System.out.println("学号信息错误，请重新输入！");
            return;
        }

        Student existing = studentService.findById(id);
        if (existing != null) {
            System.out.println("该学号已存在，请勿重复注册！");
            return;
        }

        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        System.out.println("请确认密码：");
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("密码不一致，请重新输入！");
            return;
        }

        int result = studentService.register(id, password);
        if (result > 0) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }

    private void showLoginMenu() {
        System.out.println("========== 登录界面 ==========");
        System.out.println("1. 学生登录");
        System.out.println("2. 管理员登录");
        System.out.print("请选择身份 (1-2): ");

        String choice = scanner.nextLine();

        if ("1".equals(choice)) {
            studentLogin();
        } else if ("2".equals(choice)) {
            managerLogin();
        } else {
            System.out.println("输入错误，请重新输入！");
        }
    }

    private void managerLogin() {
        System.out.println("===== 管理员登录 =====");
        System.out.println("请输入账号 (工号)：");
        String id = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        Admin admin = adminService.login(id, password);
        if (admin != null) {
            System.out.println("登录成功！欢迎 " + admin.getName() + " 登录！");
            while (true) {
                adminController.showAdminMenu(admin);
            }
        } else {
            System.out.println("登录失败！请检查账号和密码是否正确！");
        }
    }

    private void studentLogin() {
        System.out.println("===== 学生登录 =====");
        System.out.println("请输入账号 (学号)：");
        String id = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        Student student = studentService.login(id, password);
        if (student != null) {
            System.out.println("登录成功！欢迎 " + student.getName() + " 登录！");
            while (true) {
                studentController.showStudentMenu(student);
            }
        } else {
            System.out.println("登录失败！请检查账号和密码是否正确！");
        }
    }
}


