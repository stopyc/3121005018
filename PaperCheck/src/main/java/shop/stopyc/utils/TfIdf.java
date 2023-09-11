package shop.stopyc.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stopyc
 * tf-idf算法的实现
 * tf-Term Frequency-词频
 * idf-Inverse Document Frequency-逆文本频率指数
 * TF-IDF = TF * IDF
 * 其中，TF可以采用以下公式计算：
 * TF = (该单词在文章中出现的次数) / (文章中所有单词的总数)
 * 而IDF可以采用以下公式计算：
 * IDF = log(文集中的文章总数 / 含有该单词的文章数)
 *
 * 仅两篇文章作为文集，且高度相像，idf因为log(1)，将出现大量tf-idf为0的词，
 * 这极大地影响了结果
 */
public class TfIdf {
    /**
     * 计算一个词在一组词里的tf
     * @param word 要计算的词
     * @param words 词集
     * @return Tf
     * */
    public static double countTf(String word, List<String> words){int tf = 0;
        for (String s:words) {
            tf += word.equals(s) ? 1 : 0;
        }
        return tf;
    }

    /**
     * 计算一组词的每个词的Tf并返回
     * @param words 词集
     * @return 每个词的Tf
     * */
    public static List<Double> countTfs(List<String> words){
        ArrayList<Double> tfs = new ArrayList<>();
        for (String s:words) {
            tfs.add(countTf(s, words));
        }
        return tfs;
    }

    /**
     * 计算一个词在文集里的idf
     * @param word 要计算的词
     * @param papers 文集
     * @return idf
     * */
    public static double countIdf(String word, List<List<String>> papers){
        double paperContainingWord = 0;
        for(List<String> words : papers) {
            paperContainingWord += words.contains(word) ? 1 : 0;
        }
        return Math.log(papers.size() / paperContainingWord);
    }

    /**
     * 计算某组词的所有词在文集里的idf
     * @param words 要计算的词集
     * @param papers 文集
     * @return idfs
     * */
    public static List<Double> countIdfs(List<String> words, List<List<String>> papers){
        List<Double> idfs = new ArrayList<>();
        for(String word : words) {
            idfs.add(countIdf(word, papers));
        }
        return idfs;
    }

    /**
     * 计算一个词在文集里的idf
     * @param tf 词频
     * @param idf 逆文本频率指数
     * @return tf-idf
     * */
    public static double countTfIdf(double tf, double idf){
        return tf * idf;
    }

    /**
     * 计算某组词在某文集的tf-idf
     * @param words 要计算的词集
     * @param papers 文集
     * @return tf-idfs
     * */
    public static List<Double> countTfIdfs(List<String> words, List<List<String>> papers){
        List<Double> tfIdfs = new ArrayList<>();

        List<Double> tfs = countTfs(words);
        List<Double> idfs = countIdfs(words, papers);
        for(int i=0;i<tfs.size();i++){
            tfIdfs.add(TfIdf.countTfIdf(tfs.get(i), idfs.get(i)));
        }
        return tfIdfs;
    }
}
