package top.fans39.studentperformance.controller;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.studentperformance.common.Result;
import top.fans39.studentperformance.common.RoleEnum;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Admin;
import top.fans39.studentperformance.service.AdminService;
import top.fans39.studentperformance.service.StudentService;

import java.util.List;

@RestController

public class WebController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public Result hello(){
        return Result.success();
    }

    @PostMapping("/login")

    public Result login(@RequestBody Account account){
//        System.out.println("account"+account);
        Account dbAccount;
        if(RoleEnum.ADMIN.name().equals(account.getRole())){
            //        System.out.println("admin"+admin);
             dbAccount = adminService.login(account);
            //        System.out.println("db"+dbAdmin);
        }else if(RoleEnum.STUDENT.name().equals(account.getRole())){
             dbAccount = studentService.login(account);
        }else {
            return Result.error("角色不匹配");
        }

        return Result.success(dbAccount);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account account){
        if(ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())){
            return Result.error("用户名或密码不能为空");
        }
        studentService.register(account);
        return Result.success();
    }
}
