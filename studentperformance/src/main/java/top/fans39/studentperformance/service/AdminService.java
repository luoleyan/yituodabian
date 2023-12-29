package top.fans39.studentperformance.service;

import org.springframework.stereotype.Service;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Admin;

import java.util.List;


public interface AdminService {
    Account login(Account account);
}
