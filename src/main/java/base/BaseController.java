package base;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MiUserInfo;
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
//判断是否具有权限
    public boolean isPermmit(String role){
        String loginRole= getUserRole();
        if (loginRole.equals(role)){
            return true;
        }else return false;
    }
//    得到传入对象
public Object getObject(Object object){
    JSONObject jsonObject = convertRequestBody();
    object= JSONObject.toJavaObject(jsonObject,object.getClass());
    return  object;
}
    //设定信息 session
    public void setApplicationInfo(String key, Object value) {
        session.setAttribute(key, value);
    }
    //取得信息
    protected Object getApplicationInfo(String key) {
        return session.getAttribute(key);
    }
    //取得用户id
    protected Long getUserInfo() {

        MiUserInfo userInfo = (MiUserInfo) this.getApplicationInfo("user");

        return userInfo.getId();
    }
    //取得用户对象
    protected MiUserInfo getLoginUser() {

        MiUserInfo userInfo = (MiUserInfo) this.getApplicationInfo("user");

        return userInfo;
    }
    //取得用户的角色
    protected String getUserRole() {

        String userRole = (String) this.getApplicationInfo("role");

        return userRole;
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


    //IO操作
//	读取图片
    protected byte[] getPicture(String filename) throws IOException {
        File file2 = new File(filename);
        FileInputStream fis = new FileInputStream(file2);
        byte[] buf = new byte[(int) file2.length()];
        fis.read(buf);
        fis.close();
        return buf;
    }


    //	读取文档
    protected byte[] getDocument(String filename) {
        byte[] document = null;
        return document;
    }

    //	文件操作
    private static int byteIndexOf(byte[] b, String s, int start) {
        return byteIndexOf(b, s.getBytes(), start);
    }

    private static int byteIndexOf(byte[] b, byte[] s, int start) {
        int i;
        if (s.length == 0) {
            return 0;
        }
        int max = b.length - s.length;
        if (max < 0) {
            return -1;
        }
        if (start > max) {
            return -1;
        }
        if (start < 0) {
            start = 0;
        }
        // 在b中找到s的第一个元素
        search:
        for (i = start; i <= max; i++) {
            if (b[i] == s[0]) {
                // 找到了s中的第一个元素后，比较剩余的部分是否相等
                int k = 1;
                while (k < s.length) {
                    if (b[k + i] != s[k]) {
                        continue search;
                    }
                    k++;
                }
                return i;
            }
        }
        return -1;
    }

    private static byte[] subBytes(byte[] b, int from, int end) {
        byte[] result = new byte[end - from];
        System.arraycopy(b, from, result, 0, end - from);
        return result;
    }

    private static String subBytesString(byte[] b, int from, int end) {
        return new String(subBytes(b, from, end));
    }
//解析前端提交的文件，返回map
//    4.13
    protected HashMap getMultiform() throws IOException {
        HashMap<String,Map> result=new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final int NONE = 0; // 状态码，表示没有特殊操作
        final int DATAHEADER = 1; // 表示下一行要读到报头信息
        final int FILEDATA = 2; // 表示下面要读的是上传文件和二进制数据
        final int FIELDDATA = 3; // 表示下面要读到表单域的文本值
        // 请求消息实体的总长度(请求消息中除消息头之外的数据长度)
        int totalbytes = request.getContentLength();
        File f; // 上传文件储存在服务器上
        // 容纳请求消息实体的字节数组
        byte[] dataOrigin = new byte[totalbytes];
        // 对于post多个文件的表单，b作为原始数据的副本提供提取文件数据的操作
        byte[] b = new byte[totalbytes];
        // 请求消息类型
        String contentType = request.getContentType();
        String fieldname = ""; // 表单域的名称
        String fieldvalue = ""; // 表单域的值
        String fileFormName = ""; // 上传的文件再表单中的名称
        String fileRealName = ""; // 上传文件的真实名字
        String boundary = ""; // 分界符字符串
        String lastboundary = ""; // 结束分界符字符串
        int fileSize = 0; // 文件长度
        // 容纳表单域的名称/值的哈希表
        Map<String, String> formfieldsTable = new HashMap<String, String>();
        // 容纳文件域的名称/文件名的哈希表
        Map<String, String> filenameTable = new HashMap<String, String>();
        //容纳文件hushTable
        Map<String,byte[]> fileTable=new HashMap<>();
        // 在消息头类型中找到分界符的定义
        int pos = contentType.indexOf("boundary=");
        int pos2; // position2
        if (pos != -1) {
            pos += "boundary=".length();
            boundary = "--" + contentType.substring(pos); // 解析出分界符
            lastboundary = boundary + "--"; // 得到结束分界符
        }
        int state = NONE; // 起始状态为NONE
        // 得到请求消息的数据输入流
        DataInputStream in = new DataInputStream(request.getInputStream());
        in.readFully(dataOrigin); // 根据长度，将消息实体的内容读入字节数组dataOrigin中
        in.close(); // 关闭数据流
        String reqcontent = new String(dataOrigin); // 从字节数组中得到表示实体的字符串
        // 从字符串中得到输出缓冲流
        BufferedReader reqbuf = new BufferedReader(new StringReader(reqcontent));
        // 设置循环标志
        boolean flag = true;
        // int i = 0;
        while (flag == true) {
            String s = reqbuf.readLine();
            if (s == lastboundary || s == null)
                break;
            switch (state) {
                case NONE:
                    if (s.startsWith(boundary)) {
                        // 如果读到分界符，则表示下一行一个头信息
                        state = DATAHEADER;
                        // i += 1;
                    }
                    break;
                case DATAHEADER:
                    pos = s.indexOf("filename=");
                    // 先判断出这是一个文本表单域的头信息，还是一个上传文件的头信息
                    if (pos == -1) {
                        // 如果是文本表单域的头信息，解析出表单域的名称
                        pos = s.indexOf("name=");
                        pos += "name=".length() + 1; // 1表示后面的"的占位
                        s = s.substring(pos);
                        int l = s.length();
                        s = s.substring(0, l - 1); // 应该是"
                        fieldname = s; // 表单域的名称放入fieldname
                        state = FIELDDATA; // 设置状态码，准备读取表单域的值
                    } else {
                        // 如果是文件数据的头，先存储这一行，用于在字节数组中定位
                        String temp = s;
                        // 先解析出文件名
                        pos = s.indexOf("name=");
                        pos += "name=".length() + 1; // 1表示后面的"的占位
                        pos2 = s.indexOf("filename=");
                        String s1 = s.substring(pos, pos2 - 3); // 3表示";加上一个空格
                        fileFormName = s1;
                        pos2 += "filename=".length() + 1; // 1表示后面的"的占位
                        s = s.substring(pos2);
                        int l = s.length();
                        s = s.substring(0, l - 1);
                        pos2 = s.lastIndexOf("\\"); // 对于IE浏览器的设置
                        s = s.substring(pos2 + 1);
                        fileRealName = s;
                        if (fileRealName.length() != 0) { // 确定有文件被上传
                            // 下面这一部分从字节数组中取出文件的数据
                            b = dataOrigin; // 复制原始数据以便提取文件
                            pos = byteIndexOf(b, temp, 0); // 定位行
                            // 定位下一行，2 表示一个回车和一个换行占两个字节
                            b = subBytes(b, pos + temp.getBytes().length + 2,
                                    b.length);
                            // 再读一行信息，是这一部分数据的Content-type
                            s = reqbuf.readLine();
                            // 设置文件输入流，准备写文件
//                            f = new File("d:" + File.separator + fileRealName);
//                            DataOutputStream fileout = new DataOutputStream(new FileOutputStream(f));
                            // 字节数组再往下一行，4表示两回车换行占4个字节，本行的回车换行2个字节，Content-type的下
                            // 一行是回车换行表示的空行，占2个字节
                            // 得到文件数据的起始位置
                            b = subBytes(b, s.getBytes().length + 4, b.length);
                            pos = byteIndexOf(b, boundary, 0); // 定位文件数据的结尾
                            b = subBytes(b, 0, pos - 1); // 取得文件数据
//                            fileout.write(b, 0, b.length - 1); // 将文件数据存盘
//                            fileout.close();
                            fileSize = b.length - 1; // 文件长度存入fileSize
                            filenameTable.put(fileFormName, fileRealName);
                            fileTable.put(fileRealName,b);
                            state = FILEDATA;
                        }
                    }
                    break;
                case FIELDDATA:
                    // 读取表单域的值
                    s = reqbuf.readLine();
                    fieldvalue = s; // 存入fieldvalue
                    formfieldsTable.put(fieldname, fieldvalue);
                    state = NONE;
                    break;
                case FILEDATA:
                    // 如果是文件数据不进行分析，直接读过去
                    while ((!s.startsWith(boundary))
                            && (!s.startsWith(lastboundary))) {
                        s = reqbuf.readLine();
                        if (s.startsWith(boundary)) {
                            state = DATAHEADER;
                        } else {
                            break;
                        }
                    }
                    break;
            }
        }
        result.put("formfieldsTable",formfieldsTable);
        result.put("fileFormName",filenameTable);
        result.put("fileTable",fileTable);
        return  result;
    }
}