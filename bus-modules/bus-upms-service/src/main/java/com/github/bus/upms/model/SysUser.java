package com.github.bus.upms.model;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public SysUser(String userId, String userCode, String userPassword, String userName, String userSex, Date userBirthday, String userEmail, String userIcard, String userPhone, String userHeadSculpture, String userType, String userStatus, String userRemark, Date createTime, Date updateTime, String createUserId, String updateUserId, Integer flag) {
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

    public SysUser() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
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
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserIcard() {
        return userIcard;
    }

    public void setUserIcard(String userIcard) {
        this.userIcard = userIcard == null ? null : userIcard.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserHeadSculpture() {
        return userHeadSculpture;
    }

    public void setUserHeadSculpture(String userHeadSculpture) {
        this.userHeadSculpture = userHeadSculpture == null ? null : userHeadSculpture.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
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
        sb.append(", userId=").append(userId);
        sb.append(", userCode=").append(userCode);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userName=").append(userName);
        sb.append(", userSex=").append(userSex);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userIcard=").append(userIcard);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userHeadSculpture=").append(userHeadSculpture);
        sb.append(", userType=").append(userType);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userRemark=").append(userRemark);
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