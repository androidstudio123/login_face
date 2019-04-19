package com.du.lin.controller;

import com.du.lin.bean.category;
import com.du.lin.bean.commodity;
import com.du.lin.bean.login;
import com.du.lin.service.Categoryservice;
import com.du.lin.service.CommimageService;
import com.du.lin.service.CommodityService;
import com.du.lin.service.Loginservice;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CommodityController {
//  E://zjc//hmailserver-master//email//src//main//resources//static//img//comm//
    private static String UPLOADED_FOLDER = "E://zjc//hmailserver-master//email//src//main//resources//static//img//comm//";
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private Categoryservice categoryservice;
    @Autowired
    private CommimageService commimageService;
    @Autowired
    private Loginservice loginservice;

    @GetMapping("/gocomm")
    public String findBycategory_id(Model m, @RequestParam int category_id,@RequestParam(value = "start",defaultValue = "0") int start,@RequestParam(value = "size",defaultValue="8") int size) throws Exception {
        List<commodity> list = new ArrayList<>();
        list=commodityService.findBycategory_id(category_id);
        m.addAttribute("list",list);
        List<category> value = categoryservice.findvalueByid(category_id);
        category value1= value.get(0);
        m.addAttribute("categoryvalue",value1);

         PageHelper.startPage(start,size,"id desc");
        List all=commodityService.findBycategory_Id(category_id);
       PageInfo<commodity> page=new PageInfo<>(all);
        m.addAttribute("page", page);
        return "commodity";
    }
@RequestMapping("goodcomm")
    public String findQuality(Model m,@RequestParam(defaultValue ="good") String quailty,@RequestParam(value = "start",defaultValue = "0") int start,@RequestParam(value = "size",defaultValue="8") int size){
    PageHelper.startPage(start,size,"id desc");
    List<commodity> qq = new ArrayList<>();
    qq=commodityService.findByquailty(quailty);
    PageInfo<commodity> page=new PageInfo<>(qq);
    m.addAttribute("page", page);
    m.addAttribute("categoryvalue","商品良品");
        return "goodcommodity";
}

@RequestMapping("findcommodityiamgeBycommodity_id")
    public String findcommodityiamgeBycommodity_id(Model m,@RequestParam int commodity_id){
      //  List<commimage> image=commimageService.findBycommodityid(commodity_id);
        int commodity=commodityService.findlogin_idBycommodity_id(commodity_id);
        //  commimage image1=image.get(0);
          int iimage1=commodity;
        //m.addAttribute("image",image1);
       m.addAttribute("loginid",iimage1);
        return "commodityimage";
    }

    @RequestMapping("myTransanction")
    public String findcommodityBylogin_id(Model m,@RequestParam int login_id ,@RequestParam(value = "start",defaultValue = "0") int start,@RequestParam(value = "size",defaultValue="6") int size){
        PageHelper.startPage(start,size,"id desc");
      List<commodity> shelf=commodityService.findcommodityBylogin_id(login_id);
        PageInfo<commodity> page=new PageInfo<>(shelf);
        m.addAttribute("page", page);
        m.addAttribute("shelf",shelf);
        m.addAttribute("login",login_id);
        return "mycommodityshelf";
    }
    @RequestMapping("upload")
    public String upload(Model m){
       List<category> categoryList=categoryservice.findAll();
       m.addAttribute("categoryLists",categoryList);
        return "uploadsell";
    }
    @RequestMapping("savecomm")
    public String Savecomm(Model m ,commodity commodity,@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes){
        commodity.setFilename(file.getOriginalFilename());//将文件的原名字赋值给filename字段
//        commodity.setCategory_id(categoryservice.findidByname(commodity.getType()).getId());
        category a=categoryservice.findidByname(commodity.getType());
        commodity.setCategory_id(a.getId());
           commodityService.save(commodity);
        Map<Integer, String> categories = categoryservice.listByMap();
        List<commodity> list1 = new ArrayList<>();
        list1 = commodityService.findAll();
        List<login> list = new ArrayList<>();
        list = loginservice.findAll();

        for (login log : list) {
            m.addAttribute("categories", categories);
            m.addAttribute("commodities", list1);
            m.addAttribute("user", log);
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            //先将文件转换为字节序列，保证该系统可以正确读取
            //使用平台的默认字符集将字符串编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
//            Files.write()

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        m.addAttribute("img",file.getOriginalFilename());
        return "home";
    }

    @RequestMapping("deletecomm")
    public String deletecomm(Model m, HttpServletRequest request,int comm_id, @RequestParam(value = "start",defaultValue = "0") int start, @RequestParam(value = "size",defaultValue="6") int size){
       commodityService.deletecommByid(comm_id);
//        m.addAttribute("categoryLists",categoryList);
//        PageHelper.startPage(start,size,"id desc");
//       int comm=commodityService.findlogin_idBycommodity_id(comm_id);
//        List<commodity> shelf=commodityService.findcommodityBylogin_id(comm);
//        PageInfo<commodity> page=new PageInfo<>(shelf);
//        m.addAttribute("page", page);
//        m.addAttribute("shelf",shelf);
//        m.addAttribute("login",comm);
        request.getSession().getAttribute("username");
        request.getSession().getAttribute("id");
        return "home";
    }
}
