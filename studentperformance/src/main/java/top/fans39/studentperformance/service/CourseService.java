package top.fans39.studentperformance.service;

import com.github.pagehelper.PageInfo;
import top.fans39.studentperformance.entity.Course;

// 课程信息业务层接口
public interface CourseService {
    public PageInfo<Course> selectPage(Integer pageNum, Integer pageSize, Course course);

    public void add(Course course);

    public void updateByid(Course course);

    void deleteById(Integer id);
}
