package top.fans39.studentperformance.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.studentperformance.common.Result;
import top.fans39.studentperformance.entity.Course;
import top.fans39.studentperformance.entity.StudentCourse;
import top.fans39.studentperformance.service.StudentCourseService;

@RestController
@RequestMapping("/studentCourse")
public class StudentCourseController {
        @Autowired
        private StudentCourseService studentCourseService;
        @PostMapping("/add")
        public Result add(@RequestBody StudentCourse studentCourse){
        studentCourseService.add(studentCourse);
        return Result.success();
        }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, StudentCourse studentCourse){
//        System.out.println(course);
        PageInfo<StudentCourse> pageInfo = studentCourseService.selectPage(pageNum, pageSize, studentCourse);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteStudent(@PathVariable Integer id) {
        studentCourseService.deleteStudentById(id);
        return Result.success();
    }

}
