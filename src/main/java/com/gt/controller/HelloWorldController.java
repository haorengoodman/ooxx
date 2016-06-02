package com.gt.controller;

import com.gt.entity.User;
import com.gt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by journal on 2016/5/30.
 */
@Controller
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello")
    public ModelAndView hello(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mv = new ModelAndView();
        //添加模型数据 可以是任意的POJO对象
        mv.addObject("message", "WEB-INF/jsp/x/Hello World! / 你好！");
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("hello");
        return mv;
    }

    @RequestMapping(value = "/cc")
    public ModelAndView cc(){
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setDes("aa");
        User userRes = userService.insert(user);

        mv.addObject("message",userRes.getId() );
        mv.setViewName("hello");
        return mv;
    }
}
