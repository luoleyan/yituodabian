package top.fans39.studentperformance.service;

import com.github.pagehelper.PageInfo;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Student;

public interface StudentService {
    Account login(Account account);

    public void register(Account account);

    public void addStudent(Student student);

    void deleteStudentById(Integer id);

    void updateStudentById(Student student);

    PageInfo<Student> selectPage(Integer pageNum, Integer pageSize, Student student);
}
