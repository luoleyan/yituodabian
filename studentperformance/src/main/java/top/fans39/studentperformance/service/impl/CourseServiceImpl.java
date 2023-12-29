package top.fans39.studentperformance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.studentperformance.dao.CourseDao;
import top.fans39.studentperformance.entity.Course;
import top.fans39.studentperformance.service.CourseService;

import java.util.List;

@Service("CourseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {
    // 实现Service中的方法
    @Autowired
    private CourseDao courseDao;
    public PageInfo<Course> selectPage(Integer pageNum, Integer pageSize, Course course ){
        // 使用PageHelper进行分页查询
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", course.getName()).and(i-> i.like("teachers", course.getTeachers()).like("no", course.getNo()));
//        System.out.println(queryWrapper);
        List<Course> courseList = courseDao.selectList(queryWrapper);
        // 创建PageInfo对象，并返回
        return PageInfo.of(courseList);
        // 调用CourseMapper.xml中的selectPage方法
    }

    @Override
    public void add(Course course) {
        courseDao.insert(course);
    }

    @Override
    public void updateByid(Course course) {
        courseDao.updateById(course);
    }

    @Override
    public void deleteById(Integer id) {
        courseDao.deleteById(id);
    }
}
