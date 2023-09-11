package shop.stopyc.utils;

import org.junit.Before;
import org.junit.Test;
import pers.ouroborosno2.segment.AnsjSeg;
import pers.ouroborosno2.segment.TermsSegment;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TfIdfTest {

    Random r;

    List<List<String>> papers;
    List<String> originWords;
    List<String> copyWords;

    @Before
    public void start(){
        r = new Random(1000L);
        String originStr = "# 模拟原文文件（中文）\n" +
                "\n" +
                "“\n" +
                "有人住高楼,有人在深沟,有人光万丈,有人一身锈,世人万千种,浮云莫去求,斯人若彩虹,遇上方知有。\n" +
                "”\n" +
                "\n" +
                "“\n" +
                "有些人沦为平庸浅薄，金玉其外，而败絮其中。\n" +
                "可不经意间，有一天你会遇到一个彩虹般绚丽的人，从此以后，其他人就不过是匆匆浮云。\n" +
                "”\n" +
                "\n" +
                "“\n" +
                "大多数人都生活在平静的绝望中，所谓听天由命，不过是习以为常的绝望。\n" +
                "在人类的所谓游戏与消遣底下，甚至都隐藏着一种凝固的、不知又不觉的绝望。\n" +
                "两者中都没有娱乐可言，因为工作之后才能娱乐。可是不做绝望的事，才是智慧的一种表征。\n" +
                "”";
        String copyStr = "# 模拟抄袭文件（中文）\n" +
                "\n" +
                "“\n" +
                "有的人住在高楼,也有的人住在深沟,有的人光万丈,有的人一身锈,有万千种世人,浮云不要去求,这人像彩虹,遇上方就知道有。\n" +
                "”\n" +
                "\n" +
                "“\n" +
                "有些人沦为平庸浅薄，有金玉在其外，但是有败絮在其中。\n" +
                "可不经意间，有一天你会遇上一个像彩虹绚丽的人，从此以后，其他人就只是匆匆浮云。\n" +
                "”\n" +
                "\n" +
                "“\n" +
                "绝大部分人都生活在平静的绝望中，所谓的听天由命，其实是一种习以为常的绝望。\n" +
                "在人类的所谓消遣与游戏之下，甚至还隐藏着一种不知又不觉的、凝固的绝望。\n" +
                "两者中都没有娱乐可言，因为工作之后才能娱乐。可是不做绝望的事，乃智慧之表征。\n" +
                "”";
        //分词
        //开启过滤器
        TermsSegment seg = new AnsjSeg();
        seg.openFilter(true);
        originWords = seg.parseToList(originStr);
        copyWords = seg.parseToList(copyStr);

        papers = Arrays.asList(originWords, copyWords);
    }

    @Test
    public void countTf() {
        originWords.forEach(word -> {
            TfIdf.countTf(word, originWords);
        });
        copyWords.forEach(word -> {
            TfIdf.countTf(word, copyWords);
        });
    }

    @Test
    public void countTfs() {
        TfIdf.countTfs(originWords);
        TfIdf.countTfs(copyWords);
    }

    @Test
    public void countIdf() {
        originWords.forEach(word -> {
            TfIdf.countIdf(word, papers);
        });
        copyWords.forEach(word -> {
            TfIdf.countIdf(word, papers);
        });
    }

    @Test
    public void countIdfs() {
        TfIdf.countIdfs(originWords, papers);
        TfIdf.countIdfs(copyWords, papers);
    }

    @Test
    public void countTfIdf() {
        for(int i=0;i<100;i++) {
            TfIdf.countTfIdf(r.nextDouble(), r.nextDouble());
        }
    }

    @Test
    public void countTfIdfs() {
        TfIdf.countTfIdfs(originWords, papers);
        TfIdf.countTfIdfs(copyWords, papers);
    }
}