package top.fans39.studentperformance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.studentperformance.dao.AdminDao;
import top.fans39.studentperformance.entity.Account;
import top.fans39.studentperformance.entity.Admin;
import top.fans39.studentperformance.exception.CustomException;
import top.fans39.studentperformance.service.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("AdminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao,Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Account login(Account account) {
//        System.out.println(admin);
        // 实现登录逻辑
        Map<String,Object> map = new HashMap<>();
        map.put("username",account.getUsername());
        map.put("password",account.getPassword());
        List<Admin> adminList =  adminDao.selectByMap(map);
//        System.out.println(adminList);
        if(adminList.isEmpty()){
            throw new CustomException("用户名或密码或角色错误!!!");
        }

        if(!adminList.get(0).getPassword().equals(account.getPassword())){
            throw new CustomException("用户名或密码或角色错误!!!");
        }

        // 假设查询到结果，返回一个Admin对象
        Account dbAdmin =  adminList.get(0);

        return dbAdmin;
    }
    // 实现AdminService接口的方法
}
