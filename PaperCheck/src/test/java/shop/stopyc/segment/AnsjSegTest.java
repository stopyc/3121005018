package shop.stopyc.segment;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Before;
import org.junit.Test;
import shop.stopyc.utils.PaperFileUtil;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnsjSegTest {

    ArrayList<String> str;
    TermsSegment seg;

    @Before
    public void start(){
        seg = new AnsjSeg();
        seg.openFilter(false);
        seg.openFilter(true);
        str = new ArrayList<String>();
        str.add("i don't know what to say");
        str.add("Python需要将入口文件名设置成main.py，C/C++需要提供可执行文件main.exe，Java需要提供编译好的jar包main.jar");
        str.add("君问归期未有期，巴山夜雨涨秋池");
        try {
            str.add(PaperFileUtil.getStringFromFile("D:\\Code\\PaperCheck\\src\\main\\resources\\origin_cn.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseToString() {
        for (String toParse:str) {
            System.out.println(seg.parseToString(toParse," "));
        }
    }

    @Test
    public void parseToList() {
        for (String toParse:str) {
            seg.parseToList(toParse).forEach(System.out::println);
        }
    }

    @Test
    public void test(){
        ToAnalysis.parse("测试一下Term").getTerms().forEach(term -> {
            System.out.println(term.getRealName());
        });
    }
}