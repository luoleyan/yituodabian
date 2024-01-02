package top.fans39.studentperformance.service;

import com.github.pagehelper.PageInfo;
import top.fans39.studentperformance.entity.StudentCourse;

public interface StudentCourseService {
    public void add(StudentCourse studentCourse);

    PageInfo<StudentCourse> selectPage(Integer pageNum, Integer pageSize, StudentCourse studentCourse);

    void deleteStudentById(Integer id);
}
