package com.github.bus.upms.pojo;

import com.github.bus.upms.model.SysRoleMenu;

import java.util.Date;
import java.util.List;

public class RoleMenuPojo extends BasePojo {
    private String roleId;

    private String roleName;

    private String roleType;

    private String roleRemark;

    private Date createTime;

    private Date updateTime;

    private String createUserId;

    private String updateUserId;

    private Integer flag;

    private List<SysRoleMenu> roleMenuList;

    private static final long serialVersionUID = 1L;

    public RoleMenuPojo(String roleId, String roleName, String roleType, String roleRemark, Date createTime, Date updateTime, String createUserId, String updateUserId, Integer flag) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleType = roleType;
        this.roleRemark = roleRemark;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.flag = flag;
    }

    public RoleMenuPojo() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<SysRoleMenu> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<SysRoleMenu> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }
}
