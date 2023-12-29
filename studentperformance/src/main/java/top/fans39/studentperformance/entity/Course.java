package top.fans39.studentperformance.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程信息(Course)表实体类
 *
 * @author makejava
 * @since 2023-12-28 12:53:56
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Course {
//ID
    private Integer id;
//课程名称
    private String name;
//课程编号
    private String no;
//课程描述
    private String description;
//课时
    private String times;
//任课老师
    private String teachers;
}

