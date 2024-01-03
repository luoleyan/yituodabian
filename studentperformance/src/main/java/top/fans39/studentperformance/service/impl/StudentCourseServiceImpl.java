package top.fans39.studentperformance.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.fans39.studentperformance.dao.StudentCourseDao;
import top.fans39.studentperformance.entity.Student;
import top.fans39.studentperformance.entity.StudentCourse;
import top.fans39.studentperformance.exception.CustomException;
import top.fans39.studentperformance.service.StudentCourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StudentCourseService")
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseDao, StudentCourse> implements StudentCourseService {
    @Autowired
    private StudentCourseDao studentCourseDao;

    @Override
    public void add(StudentCourse studentCourse) {
        // 调用dao层的方法插入数据
        Map<String, Object> map = new HashMap<>();
        map.put("student_id", studentCourse.getStudentId());
        map.put("course_id", studentCourse.getCourseId());
        List<StudentCourse> studentCourseList = studentCourseDao.selectByMap(map);
        if(!studentCourseList.isEmpty()){
            throw new CustomException("该学生已选该课程");
            // 或者返回一个特定的错误信息
            // return Result.error("该学生已选该课程");
            // 或者抛出一个自定义的异常
            // throw new CustomException("该学生已选该课程");
            // 或者抛出一个自定义的异常并返回特定的错误信息
        }
        studentCourseDao.insert(studentCourse);
    }

    @Override
    public PageInfo<StudentCourse> selectPage(Integer pageNum, Integer pageSize, StudentCourse studentCourse) {
        // 使用PageHelper进行分页查询
        PageHelper.startPage(pageNum,pageSize);
//        System.out.println(student);
        MPJLambdaWrapper<StudentCourse> studentCourseQueryWrapper = new MPJLambdaWrapper<>();
         studentCourseQueryWrapper.select(StudentCourse::getId,StudentCourse::getName,StudentCourse::getNo,StudentCourse::getCourseId,StudentCourse::getStudentId);
         studentCourseQueryWrapper.select(Student::getName);
         studentCourseQueryWrapper.selectAs(Student::getName,StudentCourse::getStudentName);
         studentCourseQueryWrapper.leftJoin(Student.class,Student::getId,StudentCourse::getStudentId);
         studentCourseQueryWrapper.like(StringUtils.hasText(studentCourse.getName()),StudentCourse::getName,studentCourse.getName());
         studentCourseQueryWrapper.like(StringUtils.hasText(studentCourse.getNo()),StudentCourse::getNo,studentCourse.getNo());
         studentCourseQueryWrapper.eq(null != studentCourse.getId(),StudentCourse::getStudentId,studentCourse.getStudentId());

        List<StudentCourse> studentCourseList = studentCourseDao.selectJoinList(StudentCourse.class , studentCourseQueryWrapper);
//        List<Student> studentList= studentDao.selectList(null);
//        System.out.println(studentList);
        return PageInfo.of(studentCourseList);
        // 调用CourseMapper.xml中的selectPage方法
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentCourseDao.deleteById(id);
    }
}
