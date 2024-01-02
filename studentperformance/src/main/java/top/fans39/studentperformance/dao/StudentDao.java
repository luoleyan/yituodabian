package top.fans39.studentperformance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.fans39.studentperformance.entity.Student;

// 学生信息持久层接口，继承Mybatis-Puls类
public interface StudentDao extends BaseMapper<Student> {

}
