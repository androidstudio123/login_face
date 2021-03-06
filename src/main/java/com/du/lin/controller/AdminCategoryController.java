package com.du.lin.controller;

import com.du.lin.service.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminCategoryController {
    @Autowired
    private AdminCategoryService adminCategoryService;

    /**
     * 展示所有的商品类型信息，并设定数据的页数和每页展示的行数，传给js的jqgrid表格插件并展示给用户。
     * @param request
     * @return list
     */
    @ResponseBody
    @RequestMapping(value="/admincategorylist",method={RequestMethod.POST})
    public String deptList(HttpServletRequest request){
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        return adminCategoryService.getAllcategory(Integer.parseInt(page) , Integer.parseInt(rows));
    }
}
