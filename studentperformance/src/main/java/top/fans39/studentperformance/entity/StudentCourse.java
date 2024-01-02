package top.fans39.studentperformance.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (StudentCourse)表实体类
 *
 * @author makejava
 * @since 2024-01-02 09:37:15
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class StudentCourse {
//ID
    private Integer id;
//课程名称
    private String name;
//课程编号
    private String no;
//学生ID
    private Integer studentId;
//课程ID
    private Integer courseId;

    private String studentName;
}

