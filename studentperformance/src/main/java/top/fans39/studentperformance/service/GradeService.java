package top.fans39.studentperformance.service;

import com.github.pagehelper.PageInfo;
import top.fans39.studentperformance.entity.Grade;

public interface GradeService {
    public void add(Grade grade);

    PageInfo<Grade> selectPage(Integer pageNum, Integer pageSize, Grade grade);

    void update(Grade grade);

    void deleteById(Integer id);
}
