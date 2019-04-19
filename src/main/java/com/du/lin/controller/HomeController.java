package com.du.lin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.du.lin.bean.Menu;
import com.du.lin.bean.ShowNotice;
import com.du.lin.bean.User;
import com.du.lin.config.properties.LinProperties;
import com.du.lin.service.MenuService;
import com.du.lin.service.NoticeService;
import com.du.lin.utils.Userinfo;
import com.google.gson.Gson;
@Controller
public class HomeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LinProperties linProperties;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model m, HttpServletRequest request) {
		log.info("home page");
		if (!SecurityUtils.getSubject().isAuthenticated() || Userinfo.getUser() == null) {
			m.addAttribute("kaptcha", linProperties.isKptchaswich());
			return "login2";
		}
		
		//右侧的通知
		List<ShowNotice> list = noticeService.getAllShowNotice();
		m.addAttribute("noticelist", list);
		
		List<Menu> menuList = menuService.getUserMenu();
		m.addAttribute("menulist", menuList);

		m.addAttribute("username", Userinfo.getUsername());
		m.addAttribute("tip", ((User) SecurityUtils.getSubject().getPrincipal()).getRoleTip());
		m.addAttribute("sex", Userinfo.getSex());
		return "index1";

	}
	
}
