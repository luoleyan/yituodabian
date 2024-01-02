package top.fans39.studentperformance.service;

import org.springframework.stereotype.Service;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Admin;

import java.util.List;

// 管理员业务层接口
public interface AdminService {
    Account login(Account account);
}
