package shop.segment;

import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stopyc
 * 调用ansj_seg作为分词的实现
 */
public class AnsjSeg implements TermsSegment {
    private static StopRecognition filter = new StopRecognition();
    @Override
    public void openFilter(boolean b){
        if(!b){
            //false
            filter = new StopRecognition();
        }else {
            //true
            //过滤词性，标点符号太多了
//            filter.insertStopNatures("w");
            filter.insertStopNatures("null");
            //过滤单词
            filter.insertStopWords(Arrays.asList(",","，",".","。","、","”","\"","（","）"));
            //支持正则表达式
//            filter.insertStopRegexes("小.*?");
        }
    }
    @Override
    public String parseToString(String str, String...splits){
        String split = splits.length==0 ? "," : splits[0];
        return ToAnalysis.parse(str).recognition(filter).toStringWithOutNature(split);
    }
    @Override
    public List<String> parseToList(String str){
        //将字符串转Result，过滤，转Term数组，获取流，获取每个Term不含词性的内容，再收集转换成String数组
        return ToAnalysis.parse(str).recognition(filter).getTerms().stream().map(Term::getName).collect(Collectors.toList());
    }
}
