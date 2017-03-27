package base;

import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MiUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

public class BaseController {
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse Response) {
		this.session = request.getSession();
	}
//前端传回数据转json对象
	protected JSONObject convertRequestBody() {
		String param = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedReader br = request.getReader();
			String buff;
			StringBuffer buffer = new StringBuffer();
			while ((buff = br.readLine()) != null) {
				buffer.append(buff + "\n");
			}
			br.close();
			param = buffer.toString();
			return JSONObject.parseObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getParam() {
		String param = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedReader br = request.getReader();
			String buff;
			StringBuffer buffer = new StringBuffer();
			while ((buff = br.readLine()) != null) {
				buffer.append(buff);
			}
			param = buffer.toString();
			return param;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setApplicationInfo(String key, Object value) {
		session.setAttribute(key, value);
	}

	protected Object getApplicationInfo(String key) {
		return session.getAttribute(key);
	}

	protected Long getUserInfo() {

		MiUser userInfo = (MiUser) this.getApplicationInfo("user");

		return userInfo.getUserId();
	}

	protected MiUser getLoginUser() {

		MiUser userInfo = (MiUser) this.getApplicationInfo("user");

		return userInfo;
	}

	protected String getUserRole() {

		String userRole = (String) this.getApplicationInfo("userRole");

		return userRole;
	}
	//IO操作
//	读取图片
	protected byte[] getPicture(String filename) throws IOException {
		File file2 = new File(filename);
		FileInputStream fis = new FileInputStream(file2);
		byte[] buf = new byte[(int) file2.length()];
		fis.read(buf);
		fis.close();
		return  buf;
	}
//	读取文档
	protected  byte[] getDocument(String filename){
		byte[] document=null;
		return document;
	}
}