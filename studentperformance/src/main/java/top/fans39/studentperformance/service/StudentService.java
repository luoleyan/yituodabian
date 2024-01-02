package top.fans39.studentperformance.service;

import com.github.pagehelper.PageInfo;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Student;

// 学生信息业务层接口
public interface StudentService {
    // 调用账号类的Login方法实现学生的登录
    Account login(Account account);

    // 注册方法，用于学生注册账号、登录信息
    public void register(Account account);

    // 添加学生
    public void addStudent(Student student);

    // 根据Id删除学生
    void deleteStudentById(Integer id);

    // 更新学生信息
    void updateStudentById(Student student);

    PageInfo<Student> selectPage(Integer pageNum, Integer pageSize, Student student);
}
