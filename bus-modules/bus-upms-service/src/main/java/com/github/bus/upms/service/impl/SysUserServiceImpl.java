package com.github.bus.upms.service.impl;

import com.github.bus.upms.dao.SysUserMapper;
import com.github.bus.upms.dao.SysUserRoleMapper;
import com.github.bus.upms.model.SysUser;
import com.github.bus.upms.model.SysUserRole;
import com.github.bus.upms.pojo.UserPojo;
import com.github.bus.upms.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service
 *
 * @author zcq
 * @date 2018/11/13 16:17
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int deleteUser(String userId) {
        // 删除权限
        sysUserRoleMapper.deleteByUserId(userId);
        // 删除用户
        return sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public String addUser(UserPojo userPojo) {
        // 保存用户信息
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userPojo, user);
        sysUserMapper.insert(user);
        String userId = user.getUserId();
        // 保存权限
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(userPojo.getRoleId());
        userRole.setUserId(userId);
        sysUserRoleMapper.insert(userRole);
        return userId;
    }

    @Override
    public SysUser getUserByUserId(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUser(UserPojo userPojo) {
        // 修改用户信息
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userPojo, user);
        sysUserMapper.updateByPrimaryKeySelective(user);
        String userId = user.getUserId();
        // 保存权限
        sysUserRoleMapper.deleteByUserId(userId);
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(userPojo.getRoleId());
        userRole.setUserId(userId);
        sysUserRoleMapper.insert(userRole);
    }

    @Override
    public SysUser getUserByUserCode(String userCode) {
        return sysUserMapper.getUserByUserCode(userCode);
    }

    @Override
    public List<UserPojo> queryUser(UserPojo userPojo) {
        PageHelper.startPage(userPojo.getPageNum(), userPojo.getPageSize());
        return sysUserMapper.queryUser(userPojo);
    }

}
