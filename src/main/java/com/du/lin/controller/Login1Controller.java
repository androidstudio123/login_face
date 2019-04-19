package com.du.lin.controller;

import com.du.lin.bean.commodity;
import com.du.lin.bean.login;
import com.du.lin.service.CommodityService;
import com.du.lin.service.Categoryservice;
import com.du.lin.service.Loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Login1Controller {
    @Autowired
    private Loginservice loginservice;
    @Autowired
    private Categoryservice Categoryservice;
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/main", method = { RequestMethod.GET })
    public String login() {
        return "loginface";
    }

    @RequestMapping("mima")
    public String login2(Model m) {
        return "mimalogin";
    }

    @RequestMapping("main1")
    public String login1(Model m, String a, HttpServletRequest request) {
//        m.addAttribute("username",a);
        Map<Integer, String> categories = Categoryservice.listByMap();
        List<commodity> list1 = new ArrayList<>();
        list1 = commodityService.findAll();
//       List<login> log1=loginservice.findidByusername(a);
        List<login> id = loginservice.findidByusername(a);
        m.addAttribute("categories", categories);
        m.addAttribute("commodities", list1);
        request.getSession().setAttribute("id", id.get(0).getId());
//       request.getSession().setAttribute("username", log1);
        return "home";
    }

    @RequestMapping("login1")
//@ResponseBody
    public String Login(Model m, login login, HttpServletRequest request) {
//        request.getSession().setAttribute("username", login);
//        String session = (String) request.getSession().getAttribute(String.valueOf(login));
        List<login> list = new ArrayList<>();
        list = loginservice.findAll();
        Map<Integer, String> categories = Categoryservice.listByMap();
        List<commodity> list1 = new ArrayList<>();
        list1 = commodityService.findAll();
        for (login log : list) {
            if (login.getUsername().equals(log.getUsername()) && login.getPassword().equals(log.getPassword())) {
                m.addAttribute("username", login.getUsername());
                m.addAttribute("categories", categories);
                m.addAttribute("commodities", list1);
                //m.addAttribute("session", session);
                m.addAttribute("user", log);
                request.getSession().setAttribute("username", log.getUsername());
                request.getSession().setAttribute("id", log.getId());
                String session = (String) request.getSession().getAttribute(String.valueOf(log));
                return "home";
//            }else{
//                m.addAttribute("username",username);
//              m.addAttribute("err","密码错误！");
//              return "login";
//            }
//          }else {
//              m.addAttribute("err1","用户名不存在！");
//             return "login";
//         }
            }

        }
        m.addAttribute("err", "用户名或密码错误！");
        return "mimalogin";
    }

    @RequestMapping("loginin")
    public String loginin(Model m) {
        Map<Integer, String> categories = Categoryservice.listByMap();
        List<commodity> list1 = new ArrayList<>();
        list1 = commodityService.findAll();
        List<login> list = new ArrayList<>();
        list = loginservice.findAll();

        for (login log : list) {
            m.addAttribute("categories", categories);
            m.addAttribute("commodities", list1);
            m.addAttribute("user", log);
        }
        return "home";
    }


    @RequestMapping("register")
    //@ResponseBody
    public String register(Model m, login list) {
        //login log = JSON
        //List为接口不能实例化
        List<login> list1 = new ArrayList<>();
        list1 = loginservice.findAll();

        if (list.getUsername() == "" || list.getPassword() == "") {
            m.addAttribute("err1", "用户名或密码不能为空！");
            return "mimaregister";
        } else {
            for (login login : list1) {
                if ((list.getUsername()).equals(login.getUsername())) {
                    m.addAttribute("err2", "用户名已存在！");
                    return "mimaregister";
                }
            }
            loginservice.save(list);
            return "mimalogin";

        }

    }

    @RequestMapping("Register")
    public String Register() {
        return "mimaregister";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        return "mimalogin";
    }


    @RequestMapping("myMessage")
    public String findcommodityBylogin_id(Model m,int login_id){
//        PageHelper.startPage(start,size,"id desc");
       // List<login> shelf= new ArrayList<>();
//        List<login> list = new ArrayList<>();
//        list = loginservice.findAllByid(login_id);
        login my=loginservice.findAllByid(login_id);
//        PageInfo<login> page=new PageInfo<>(shelf);
//        m.addAttribute("page", page);
//        m.addAttribute("shelf",shelf);
//        m.addAttribute("login",login_id);
        m.addAttribute("my",my);
        return "mymessage";
    }
}
