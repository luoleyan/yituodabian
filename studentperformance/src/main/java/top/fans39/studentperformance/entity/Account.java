package top.fans39.studentperformance.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Account {
    private String username;
    private String password;
    private String name;
    private String role;
    // 省略其他属性和方法
}
