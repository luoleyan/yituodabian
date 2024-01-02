package top.fans39.studentperformance.service;

import com.github.pagehelper.PageInfo;
import top.fans39.studentperformance.entity.Course;

// 课程信息业务层接口
public interface CourseService {
    // 初始查询并展示，同时提供查询和分页功能
    public PageInfo<Course> selectPage(Integer pageNum, Integer pageSize, Course course);

    // 添加课程信息
    public void add(Course course);

    // 根据Id更新课程信息
    public void updateByid(Course course);

    // 根据Id删除课程
    void deleteById(Integer id);
}
