package shop.stopyc.segment;

import java.util.List;

/**
 * @author stopyc
 * 分词接口
 */
public interface TermsSegment {
    /**
     * 是否打开过滤器，默认关闭
     * @param b true or false
     * */
    void openFilter(boolean b);
    /**
     * 将字符串分词并输出为字符串
     * @param str 要分词的字符串
     * @param splits 可选分隔符，默认使用逗号作分隔符
     * @return 分词结果
     * */
    String parseToString(String str, String...splits);
    /**
     * 将字符串分词并输出为List
     * @param str 要分词的字符串
     * @return 分词结果
     * */
    List<String> parseToList(String str);
}
