package top.fans39.studentperformance.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.studentperformance.common.Result;
import top.fans39.studentperformance.entity.Course;
import top.fans39.studentperformance.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    // 课程管理相关接口
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize,Course course){
//        System.out.println(course);
        PageInfo<Course> pageInfo = courseService.selectPage(pageNum, pageSize, course);
        return Result.success(pageInfo);
    }

    @PostMapping("/add")
    public Result add (@RequestBody Course course){
        courseService.add(course);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Course course){
        courseService.updateByid(course);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        courseService.deleteById(id);
        return Result.success();
    }

}
