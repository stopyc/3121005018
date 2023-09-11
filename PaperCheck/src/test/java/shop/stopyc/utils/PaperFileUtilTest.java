package shop.stopyc.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class PaperFileUtilTest {

    String originPath;
    String copyPath;
    String targetPath;

    @Before
    public void start(){
        originPath = "D:\\Code\\PaperCheck\\src\\main\\resources\\origin_cn.txt";
        copyPath = "D:\\Code\\PaperCheck\\src\\main\\resources\\copy_cn.txt";
        targetPath = "D:\\Code\\PaperCheck\\src\\main\\resources\\target_cn.txt";
    }

    @Test
    public void getStringFromFile() {
        //读文件
        String originStr = null;
        String copyStr = null;
        try{
            originStr = PaperFileUtil.getStringFromFile(originPath);
            copyStr = PaperFileUtil.getStringFromFile(copyPath);
        }catch (IOException e) {
            System.out.println("找不到文件或读取出错");
            e.printStackTrace();
            return;
        }
        System.out.println(originStr);
        System.out.println(copyStr);
    }

    @Test
    public void write() {
        //输出答案
        try {
            PaperFileUtil.write(targetPath, Arrays.asList("答案"));
        } catch (IOException e) {
            System.out.println("输出答案出错");
            e.printStackTrace();
            return;
        }
    }
}