package com.github.bus.upms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 保存、更新操作公共字段
 *
 * @author zcq
 * @date 2018/10/23 21:06
 */
@Component
@Aspect
public class MapperAspectHandler {

    private String userId;// TODO:需要完善zcq

    @Before("execution(* com.github.bus.upms.dao..*.insert*(..))")
    public void saveDataBefore(JoinPoint joinPoint) throws Throwable {

        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Field[] fields = joinPoint.getArgs()[i].getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                Method method = null;
                if (fields[j].getName().equals("createUserId")) {
                    method = joinPoint.getArgs()[i].getClass().getMethod("setCreateUserId", String.class);
                    method.invoke(joinPoint.getArgs()[i], getUserId());
                }
                if (fields[j].getName().equals("updateUserId")) {
                    method = joinPoint.getArgs()[i].getClass().getMethod("setUpdateUserId", String.class);
                    method.invoke(joinPoint.getArgs()[i], getUserId());
                }
                if (fields[j].getName().equals("creatTime")) {
                    method = joinPoint.getArgs()[i].getClass().getMethod("setCreateTime", Date.class);
                    method.invoke(joinPoint.getArgs()[i], new Date());
                }
                if (fields[j].getName().equals("updateTime")) {
                    method = joinPoint.getArgs()[i].getClass().getMethod("setUpdateTime", Date.class);
                    method.invoke(joinPoint.getArgs()[i], new Date());
                }
                if (fields[j].getName().equals("flag")) {
                    method = joinPoint.getArgs()[i].getClass().getMethod("setFlag", Integer.class);
                    method.invoke(joinPoint.getArgs()[i], 1);
                }
            }
        }
    }

    @Before("execution(* com.github.bus.upms.dao..*.update*(..))")
    public void updateDataBefore(JoinPoint joinPoint) throws Throwable {

        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Field[] fields = joinPoint.getArgs()[i].getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                Method method = null;
                if (fields[j].getName().equals("updateUserId")) {
                    method = joinPoint.getArgs()[i].getClass().getMethod("setUpdateUserId", String.class);
                    method.invoke(joinPoint.getArgs()[i], getUserId());
                }
            }
        }
    }

    /**
     * 获取登录人账号
     *
     * @return java.lang.String
     * @author zcq
     * @date 2018/10/21 16:12
     */
    public String getUserId() {
        String userId = "0";
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            Object object = authentication.getPrincipal();
//            if (object != null) {
//                String userJson = JSONUtil.toJsonString(object);
//                UserDetail user = JSONUtil.parseObject(userJson, UserDetail.class);
//                userId = StringUtils.isNotBlank(user.getUserId()) ? user.getUserId() : "0";
//            }
//        }
        return userId;
    }

}
