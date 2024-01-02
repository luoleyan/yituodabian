package top.fans39.studentperformance.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 课程信息实体类，用于封装课程信息
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Course {
    // 课程ID
    private Integer id;
    // 课程名称
    private String name;
    // 课程编号
    private String no;
    // 课程描述
    private String description;
    // 课时
    private String times;
    // 任课老师
    private String teachers;
}

