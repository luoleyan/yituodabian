package top.fans39.studentperformance.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.studentperformance.common.Result;
import top.fans39.studentperformance.entity.Course;
import top.fans39.studentperformance.entity.Grade;
import top.fans39.studentperformance.service.GradeService;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping("/add")
    public Result add(@RequestBody Grade grade){
        gradeService.add(grade);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Grade grade){
        gradeService.update(grade);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Grade grade){
//        System.out.println(course);
        PageInfo<Grade> pageInfo = gradeService.selectPage(pageNum, pageSize, grade);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        gradeService.deleteById(id);
        return Result.success();
    }

}
