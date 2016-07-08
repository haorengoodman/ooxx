package com.gt.shiro.chapter7.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by journal on 2016/7/7.
 */
@Controller
public class Chapter7Controller {
    @RequestMapping(value = "/login")
    public ModelAndView xx(String username,String password){
        ModelAndView mv = new ModelAndView();

        String error = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if(error != null) {//出错了，返回登录页面
            mv.addObject("error", error);
            mv.setViewName("shiro/login");
        } else {//登录成功
            mv.setViewName("shiro/loginSuccess");
        }
        return mv;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(){
        ModelAndView mv = new ModelAndView();
        SecurityUtils.getSubject().logout();
        mv.setViewName("shiro/logoutSuccess");
        ThreadContext.unbindSubject();
        return mv;
    }

    @RequestMapping(value = "/formfilterlogin")
    public ModelAndView xxx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        String errorClassName = (String)req.getAttribute("shiroLoginFailure");

        if(UnknownAccountException.class.getName().equals(errorClassName)) {
            mv.addObject("error", "用户名/密码错误");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            mv.addObject("error", "用户名/密码错误");
        } else if(errorClassName != null) {
            req.setAttribute("error", "未知错误：" + errorClassName);
            mv.addObject("error","未知错误：" + errorClassName);
        }

        mv.setViewName("shiro/formfilterlogin");
        return mv;
    }

    @RequestMapping(value = "/permission")
    public ModelAndView permission() throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("user:create");
        mv.setViewName("shiro/hasPermission");
        return mv;
    }

    @RequestMapping(value = "/role")
    public ModelAndView role() throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal().toString());
        subject.checkRole("admin");
        mv.addObject("subject",subject);
        mv.setViewName("shiro/hasRole");
        //ThreadContext.unbindSubject();
        return mv;
    }

    @RequestMapping(value = "/unauthorized")
    public ModelAndView unauthorized() throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shiro/unauthorized");
        return mv;
    }


    @RequestMapping(value = "/authenticated")
    public ModelAndView authenticated(){
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        //System.out.println(subject.getPrincipal().toString());
        if(subject.isAuthenticated()) {
            mv.addObject("subject",subject);
            mv.setViewName("shiro/authenticated");
        } else {
            mv.setViewName("shiro/login");
        }
        return mv;
    }

}
