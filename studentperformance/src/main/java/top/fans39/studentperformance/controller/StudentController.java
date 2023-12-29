package top.fans39.studentperformance.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import top.fans39.studentperformance.common.Result;
import top.fans39.studentperformance.entity.Student;
import top.fans39.studentperformance.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Result addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateStudent(@RequestBody Student student) {
        studentService.updateStudentById(student);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectStudentPage(@RequestParam(defaultValue ="1" ) Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Student student) {
        // 调用业务层方法，实现分页查询
//        System.out.println(student);
        PageInfo<Student> pageInfo = studentService.selectPage(pageNum, pageSize, student);
        return Result.success(pageInfo);
    }
}
