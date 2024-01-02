package top.fans39.studentperformance.entity;

import lombok.Data;
import lombok.ToString;

// Account类，封装学生账号信息
@Data
@ToString
public class Account {
    // 用户名
    private String username;
    // 密码
    private String password;
    // 学生姓名
    private String name;
    // 身份，如学生、老师
    private String role;
    // 省略其他属性和方法
}
