package top.fans39.studentperformance.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 成绩(Grade)表实体类
 *
 * @author makejava
 * @since 2024-01-02 22:04:09
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Grade {
//ID
    private Integer id;
//课程ID
    private Integer courseId;
//学生ID
    private Integer studentId;
//成绩
    private Double score;
//教师评语
    private String comment;
//学生反馈
    private String feedback;

    private String studentName;

    private String courseName;
}

