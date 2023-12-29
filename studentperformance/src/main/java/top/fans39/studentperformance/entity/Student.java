package top.fans39.studentperformance.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 学生消息(Student)表实体类
 *
 * @author makejava
 * @since 2023-12-28 17:32:38
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Account {
//ID
    private Integer id;
//学号
    private String username;
//密码
    private String password;
//姓名
    private String name;
//手机号
    private String phone;
//邮箱
    private String email;
//性别
    private String sex;
//生日
    private String birth;
//头像
    private String avatar;
    private String role;
}

