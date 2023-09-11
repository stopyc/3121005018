package shop.stopyc;


import shop.stopyc.check.CheckAlgorithm;
import shop.stopyc.check.CosAlgorithm;
import shop.stopyc.segment.AnsjSeg;
import shop.stopyc.segment.TermsSegment;
import shop.stopyc.utils.PaperFileUtil;
import shop.stopyc.utils.TfIdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stopyc
 * the entrance
 */
public class Main {
    public static void main(String[] args){
        //读参
        if(args.length < 3){
            System.out.println("warning:请在命令行参数按顺序提供原文文件、抄袭文件、答案文件的绝对地址");
            return;
        }
        String originPath = args[0];
        String copyPath = args[1];
        String targetPath = args[2];

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

        //分词
        //开启过滤器
        TermsSegment seg = new AnsjSeg();
        seg.openFilter(true);
        List<String> originWords = seg.parseToList(originStr).stream().distinct().collect(Collectors.toList());
        List<String> copyWords = seg.parseToList(copyStr);

        //计算tf-idf
        List<List<String>> papers = Arrays.asList(originWords, copyWords);
        List<Double> originTfs = TfIdf.countTfs(originWords);
        List<Double> copyTfs = TfIdf.countTfs(copyWords);
        List<Double> originTfIdfs = TfIdf.countTfIdfs(originWords, papers);
        List<Double> copyTfIdfs = TfIdf.countTfIdfs(copyWords, papers);

        //计算相似度
        List<Double> targetValue = new ArrayList<>();
        //余弦算法
        CheckAlgorithm checkAlgorithm = new CosAlgorithm();
        targetValue.add(checkAlgorithm.check(originTfIdfs, copyTfIdfs));
        //余弦算法(以tf替代tf-idf)
        targetValue.add(checkAlgorithm.check(originTfs, copyTfs));

        //输出答案
        try {
            PaperFileUtil.write(targetPath, Arrays.asList(
                    "相似度",
                    "余弦算法："+targetValue.get(0),
                    "余弦算法(以tf替代tf-idf)："+targetValue.get(1)));
        } catch (IOException e) {
            System.out.println("输出答案出错");
            e.printStackTrace();
            return;
        }

    }
}
