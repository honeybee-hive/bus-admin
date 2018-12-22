package com.github.bus.upms.pojo;

import java.util.Date;

/**
 * 用戶POJO
 *
 * @author zcq
 * @date 2018/10/23 20:54
 */
public class UserPojo extends BasePojo {
    private String userId;

    private String userCode;

    private String userPassword;

    private String userName;

    private String userSex;

    private Date userBirthday;

    private String userEmail;

    private String userIcard;

    private String userPhone;

    private String userHeadSculpture;

    private String userType;

    private String userStatus;

    private String userRemark;

    private Date createTime;

    private Date updateTime;

    private String createUserId;

    private String updateUserId;

    private Integer flag;

    private String roleId;

    public UserPojo() {
    }

    public UserPojo(String userId, String userCode, String userPassword, String userName, String userSex, Date userBirthday, String userEmail, String userIcard, String userPhone, String userHeadSculpture, String userType, String userStatus, String userRemark, Date createTime, Date updateTime, String createUserId, String updateUserId, Integer flag) {
        this.userId = userId;
        this.userCode = userCode;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userEmail = userEmail;
        this.userIcard = userIcard;
        this.userPhone = userPhone;
        this.userHeadSculpture = userHeadSculpture;
        this.userType = userType;
        this.userStatus = userStatus;
        this.userRemark = userRemark;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserIcard() {
        return userIcard;
    }

    public void setUserIcard(String userIcard) {
        this.userIcard = userIcard;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserHeadSculpture() {
        return userHeadSculpture;
    }

    public void setUserHeadSculpture(String userHeadSculpture) {
        this.userHeadSculpture = userHeadSculpture;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
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
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
