package top.fans39.studentperformance.entity;

import lombok.Data;
import lombok.ToString;

// Admin类，封装管理员信息
@Data
@ToString
public class Admin extends Account {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String role;

    // 省略构造方法、getter和setter方法
}
