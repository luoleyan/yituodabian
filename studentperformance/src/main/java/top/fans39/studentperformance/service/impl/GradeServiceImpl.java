package top.fans39.studentperformance.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.fans39.studentperformance.dao.GradeDao;
import top.fans39.studentperformance.entity.Course;
import top.fans39.studentperformance.entity.Grade;
import top.fans39.studentperformance.entity.Student;
import top.fans39.studentperformance.entity.StudentCourse;
import top.fans39.studentperformance.exception.CustomException;
import top.fans39.studentperformance.service.GradeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("GradeService")
public class GradeServiceImpl extends ServiceImpl<GradeDao, Grade> implements GradeService {
    @Autowired
    private GradeDao gradeDao;

    @Override
    public void add(Grade grade) {
        Map<String, Object> map = new HashMap<>();
        map.put("student_id", grade.getStudentId());
        map.put("course_id", grade.getCourseId());
        List<Grade> dbGrade = gradeDao.selectByMap(map);
        if(dbGrade.isEmpty()){
            gradeDao.insert(grade);
        }else {
            throw new CustomException("您已录入该课程的成绩");
        }
    }

    @Override
    public PageInfo<Grade> selectPage(Integer pageNum, Integer pageSize, Grade grade) {
        // 使用PageHelper进行分页查询
        PageHelper.startPage(pageNum,pageSize);
//        System.out.println(student);
        MPJLambdaWrapper<Grade> gradeMPJLambdaWrapper = new MPJLambdaWrapper<>();
        gradeMPJLambdaWrapper.select(Grade::getId,Grade::getScore,Grade::getCourseId,Grade::getStudentId, Grade::getFeedback, Grade::getComment);
        gradeMPJLambdaWrapper.select(Student::getName);
        gradeMPJLambdaWrapper.selectAs(Student::getName,Grade::getStudentName);
        gradeMPJLambdaWrapper.selectAs(Course::getName, Grade::getCourseName);
        gradeMPJLambdaWrapper.leftJoin(Student.class,Student::getId,Grade::getStudentId);
        gradeMPJLambdaWrapper.leftJoin(Course.class,Course::getId,Grade::getCourseId);
        gradeMPJLambdaWrapper.like(StringUtils.hasText(grade.getStudentName()),Grade::getStudentName,grade.getStudentName());
        gradeMPJLambdaWrapper.like(StringUtils.hasText(grade.getCourseName()),Grade::getCourseName,grade.getCourseName());
        gradeMPJLambdaWrapper.eq(null != grade.getId(),Grade::getStudentId,grade.getStudentId());

        List<Grade> studentCourseList = gradeDao.selectJoinList(Grade.class , gradeMPJLambdaWrapper);
//        List<Student> studentList= studentDao.selectList(null);
//        System.out.println(studentList);
        return PageInfo.of(studentCourseList);
        // 调用CourseMapper.xml中的selectPage方法
    }

    @Override
    public void update(Grade grade) {
        gradeDao.updateById(grade);
    }

    @Override
    public void deleteById(Integer id) {
        gradeDao.deleteById(id);
    }
}
