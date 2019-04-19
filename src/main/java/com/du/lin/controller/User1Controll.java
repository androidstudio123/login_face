package com.du.lin.controller;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import com.du.lin.bean.login;
import com.du.lin.service.Loginservice;
import com.du.lin.utils.FileUtil;
import com.du.lin.utils.GetTon;
import com.du.lin.utils.GsonUtils;
import com.du.lin.utils.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.Base64.Decoder;

/**
 * 人脸识别后台控制器
* @author likang
* @date 2018年8月9日
 */
@Controller
public class User1Controll {
	private static String UPLOADED_FOLDER = "D://hMailserver//hmailserver//hmailserver//email//src//main//resources//static//picture//";
	private static String accessToken;

//	@Autowired
//	private FaceService faceService;
	@Autowired
	private Loginservice loginservice;

	@RequestMapping("registerface")
	public String login() {
		return "face1";
	}

	/**
	 * 用户人脸识别头像录入(每个用户建议只允许录入一次)
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("reg")
	public @ResponseBody void registe(HttpServletRequest request, HttpServletResponse response, Model model) {
		String img = request.getParameter("img"); // 图像数据
		String username = request.getParameter("username"); // 用户名
		// 注册百度云 人脸识别 提供的信息
		String APP_ID = "15295584";
		String API_KEY = "dSAKMM2hDZhFkYGGtoFHEHlh";
		String SECRET_KEY = "PI1iynqTQnotj7nAf3bKtNTqQuVCzzuT";
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		face(username, img, response, request, client);
	}

	/**
	 *作用：	  1.将base64编码的图片保存 
	 *		  2.将图片路径保存在数据库里面
	 *        3.将人脸图片识别在人脸库中注册 
	 */
	public void face(String username, String img, HttpServletResponse response, HttpServletRequest request,
			AipFace client) {
		//Users user = new Users();
		login login=new login();
		// 图片名称
		String fileName = System.currentTimeMillis() + ".png";
		//String basePath = request.getSession().getServletContext().getRealPath("picture/");

		// 往数据库里面插入注册信息
//		user.setUsername(username);
//		user.setHeadphoto("/picture/" + fileName);
//		faceService.save(user);
		// 往服务器里面上传图片
		GenerateImage(img, UPLOADED_FOLDER + "/" + fileName);
		// 给人脸库中加入一个脸
		boolean flag = facesetAddUser(client, fileName, username,img);
		try {
			PrintWriter out = response.getWriter();
			// 中文乱码，写个英文比较专业 哈哈
			if (flag == false) {
				out.print("Please aim at the camera!!");// 请把脸放好咯
			} else {
//				    user.setUsername(username);
//					user.setHeadphoto("/picture/" + fileName);
				login.setUsername(username);
				login.setPassword("123456");
				login.setHeadphoto("/picture/" + fileName);
					//faceService.save(user);
					loginservice.save(login);
					out.print("Record the success of the image!!"); // 注册成功
				}
//						user.setUsername(username);
//						user.setHeadphoto("/picture/" + fileName);
//						faceService.save(user);
//						out.print("Record the success of the image!!"); // 注册成功
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean GenerateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) // 图像数据为空
			return false;
		Decoder decoder = Base64.getDecoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decode(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	

	public boolean facesetAddUser(AipFace client, String path, String username, String img) {
		
		 String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", img);
	            map.put("group_id", "a");
	            map.put("user_id", username);
	            map.put("user_info", "abc");
	            map.put("liveness_control", "NORMAL");
	            map.put("image_type","BASE64");
	            map.put("quality_control", "LOW");

	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            accessToken = GetTon.getToken();

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            System.out.println(result);
	            
	            JSONObject fromObject = JSONObject.fromObject(result);

	            Object errormsg = fromObject.get("error_msg");
					if (errormsg.toString().equals("SUCCESS")) {
						return true;
					}
	            return false; 
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	}

	 public String search(String img) {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", img);
	            map.put("liveness_control", "NORMAL");
	            map.put("group_id_list", "a");
	            map.put("image_type", "BASE64");
	            map.put("quality_control", "LOW");

	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            accessToken = GetTon.getToken();

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            System.out.println(result);
	            JSONObject fromObject = JSONObject.fromObject(result);
	            JSONObject resultscore = fromObject.getJSONObject("result");
	            JSONArray jsonArray = resultscore.getJSONArray("user_list");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					double resultList = object.getDouble("score");
					String list=object.getString("user_id");
					System.out.println(resultList);
					if (resultList >= 90) {
						return list;
					}
				}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	
	
	 /**
	  * 用户人脸识别登录功能
	  * @param request
	  * @param response
	  * @param model
	  * @return
	  */
	@RequestMapping("face")
	public @ResponseBody String onListStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		String img = request.getParameter("img"); // 图像数据
		try {
			String tag = search(img);
//			PrintWriter writer = response.getWriter();
			if (tag!=null) {
				request.getSession().setAttribute("username", tag);
//				writer.print(tag);
//				writer.close();
				return tag;
			}else {
//				writer.print(tag);
//				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.jsp";
		}
		return null;
	}

	
	public boolean getResult(HttpServletRequest request, String imStr1, String imgStr2) {

		accessToken = GetTon.getToken();
		boolean flag = false;
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		try {
			 byte[] bytes1 = FileUtil.readFileByBytes("");
			 byte[] bytes2 = FileUtil.readFileByBytes("");
			 String image1 = Base64Util.encode(bytes1);
			 String image2 = Base64Util.encode(bytes2);

			List<Map<String, Object>> images = new ArrayList<>();

			Map<String, Object> map1 = new HashMap<>();
			map1.put("image", image1);
			map1.put("image_type", "BASE64");
			map1.put("face_type", "LIVE");
			map1.put("quality_control", "LOW");
			map1.put("liveness_control", "NORMAL");

			Map<String, Object> map2 = new HashMap<>();
			map2.put("image", image2);
			map2.put("image_type", "BASE64");
			map2.put("face_type", "LIVE");
			map2.put("quality_control", "LOW");
			map2.put("liveness_control", "NORMAL");

			images.add(map1);
			images.add(map2);

			String param = GsonUtils.toJson(images);

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间，
			// 客户端可自行缓存，过期后重新获取。

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			// return result;
			JSONObject fromObject = JSONObject.fromObject(result);

			JSONArray jsonArray = fromObject.getJSONArray("result");

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				double resultList = object.getDouble("score");
				if (resultList >= 90) {
					flag = true;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
