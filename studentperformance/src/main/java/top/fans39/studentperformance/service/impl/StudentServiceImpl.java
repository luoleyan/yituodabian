package top.fans39.studentperformance.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.fans39.studentperformance.common.RoleEnum;
import top.fans39.studentperformance.dao.StudentDao;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Student;
import top.fans39.studentperformance.exception.CustomException;
import top.fans39.studentperformance.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StudentService")
public class  StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Account login(Account account) {
        //        System.out.println(admin);
        // 实现登录逻辑
        Map<String,Object> map = new HashMap<>();
        map.put("username",account.getUsername());
        map.put("password",account.getPassword());
//        System.out.println(map);
        List<Student> studentList =  studentDao.selectByMap(map);
//        System.out.println(adminList);

        if(studentList.isEmpty()){
            throw new CustomException("用户名或密码或角色错误!!!");
        }

        if(!studentList.get(0).getPassword().equals(account.getPassword())){
            throw new CustomException("用户名或密码或角色错误!!!");
        }


        // 假设查询到结果，返回一个Student对象
        Account dbStudent =  studentList.get(0);

        return dbStudent;
    }

    @Override
    public void register(Account account) {
        Student student = new Student();
        student.setUsername(account.getUsername());
        student.setPassword(account.getPassword());
        student.setRole(RoleEnum.STUDENT.name());
        Map<String,Object> map = new HashMap<>();
        map.put("username",account.getUsername());
        List<Student> studentList = studentDao.selectByMap(map);
        if(studentList.isEmpty()){
            if (ObjectUtil.isEmpty(student.getName())) {
                student.setName(student.getUsername());
            }
            studentDao.insert(student);
        } else {
            throw new CustomException("该用户已经注册 !!!");
        }
    }

    @Override
    public void addStudent(Student student) {
        student.setRole(RoleEnum.STUDENT.name());
        Map<String,Object> map = new HashMap<>();
        map.put("username",student.getUsername());
        List<Student> studentList = studentDao.selectByMap(map);
        if(studentList.isEmpty()){
            if (ObjectUtil.isEmpty(student.getName())) {
                student.setName(student.getUsername());
            }
            studentDao.insert(student);
        } else {
            throw new CustomException("该学生已注册 !!!");
        }
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public void updateStudentById(Student student) {
        studentDao.updateById(student);
    }

    @Override
    public PageInfo<Student> selectPage(Integer pageNum, Integer pageSize, Student student) {
        // 使用PageHelper进行分页查询
        PageHelper.startPage(pageNum,pageSize);
//        System.out.println(student);
        LambdaQueryWrapper<Student> studentQueryWrapper = new LambdaQueryWrapper<>();
        studentQueryWrapper.like(StringUtils.hasText(student.getName()),Student::getName,student.getName());
        studentQueryWrapper.like(StringUtils.hasText(student.getUsername()),Student::getUsername,student.getUsername());
        studentQueryWrapper.like(StringUtils.hasText(student.getPhone()),Student::getPhone,student.getPhone());
        studentQueryWrapper.like(StringUtils.hasText(student.getEmail()),Student::getEmail,student.getEmail());
        List<Student> studentList = studentDao.selectList(studentQueryWrapper);
//        List<Student> studentList= studentDao.selectList(null);
//        System.out.println(studentList);
        return PageInfo.of(studentList);
        // 调用CourseMapper.xml中的selectPage方法
    }
    // 实现AdminService接口的方法
    }

