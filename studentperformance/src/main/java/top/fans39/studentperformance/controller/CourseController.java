package top.fans39.studentperformance.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.studentperformance.common.Result;
import top.fans39.studentperformance.entity.Course;
import top.fans39.studentperformance.service.CourseService;

// CourseController接口用于进行课程相关操作
@RestController
@RequestMapping("/course")
public class CourseController {
    // 注入Service层接口
    @Autowired
    private CourseService courseService;
    // 课程管理相关接口
    // 查询课程信息并展示在页面上
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize,Course course){
//        System.out.println(course);
        PageInfo<Course> pageInfo = courseService.selectPage(pageNum, pageSize, course);
        return Result.success(pageInfo);
    }
    // 添加课程信息
    @PostMapping("/add")
    public Result add (@RequestBody Course course){
        courseService.add(course);
        return Result.success();
    }
    // 更新课程信息
    @PutMapping("/update")
    public Result update(@RequestBody Course course){
        courseService.updateByid(course);
        return Result.success();
    }
    // 根据id删除课程
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        courseService.deleteById(id);
        return Result.success();
    }

}
