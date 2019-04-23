package com.du.lin.controller;

import com.du.lin.bean.login;
import com.du.lin.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @ResponseBody
    @RequestMapping(value = "/adminloginlist",method = {RequestMethod.POST})
    public String loginList(HttpServletRequest request){
       String page=request.getParameter("page");
       String row=request.getParameter("rows");
        return adminLoginService.getAlllogin(Integer.parseInt(page),Integer.parseInt(row));
    }
}
