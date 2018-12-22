package com.github.bus.upms.model;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    private String menuId;

    private String menuName;

    private String menuUrl;

    private String menuIcon;

    private Integer menuType;

    private Integer menuStatus;

    private Integer menuGrade;

    private Integer menuOrder;

    private String menuParent;

    private String menuRemark;

    private Date createTime;

    private Date updateTime;

    private String createUserId;

    private String updateUserId;

    private Integer flag;

    private static final long serialVersionUID = 1L;

    public SysMenu(String menuId, String menuName, String menuUrl, String menuIcon, Integer menuType, Integer menuStatus, Integer menuGrade, Integer menuOrder, String menuParent, String menuRemark, Date createTime, Date updateTime, String createUserId, String updateUserId, Integer flag) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.menuType = menuType;
        this.menuStatus = menuStatus;
        this.menuGrade = menuGrade;
        this.menuOrder = menuOrder;
        this.menuParent = menuParent;
        this.menuRemark = menuRemark;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.flag = flag;
    }

    public SysMenu() {
        super();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(Integer menuGrade) {
        this.menuGrade = menuGrade;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(String menuParent) {
        this.menuParent = menuParent == null ? null : menuParent.trim();
    }

    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark == null ? null : menuRemark.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuType=").append(menuType);
        sb.append(", menuStatus=").append(menuStatus);
        sb.append(", menuGrade=").append(menuGrade);
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", menuParent=").append(menuParent);
        sb.append(", menuRemark=").append(menuRemark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", flag=").append(flag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}