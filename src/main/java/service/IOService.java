package service;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Manlin on 2017/4/12.
 */
@Service
public class IOService {

    //写入文件
    public int writeFile(String path, String name, byte[] data) {
        File f = new File(path, File.separator + name);
        try {
            DataOutputStream fileout = new DataOutputStream(new FileOutputStream(f));
            fileout.write(data, 0, data.length - 1); // 将文件数据存盘
            fileout.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 300;
        }
        return 200;
    }

    //取得文件类型
    public String getType(String filename) {
        String type = null;
        int pos = -1;
        pos = filename.indexOf(".");
        if (pos != -1) {
            type=filename.substring(pos,filename.length());
        }
        return type;
    }
}
