package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by wanghuan on 2017/3/17.
 */
@Controller
public class TestCotroller {
    @RequestMapping("/hello")
    @ResponseBody
    public void hello(HttpServletResponse res) throws IOException {
        String data="HELLO";
        res.setCharacterEncoding("utf-8");
        res.setHeader("Content-Type","text/html;charset=UTF-8");
        byte[] bytedata=data.getBytes("UTF-8");
        res.getOutputStream().write(bytedata);
    }
}
