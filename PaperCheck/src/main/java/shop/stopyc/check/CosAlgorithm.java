package shop.stopyc.check;

import java.util.List;

/**
 * @author stopyc
 * 余弦相似度查重算法的实现
 */
public class CosAlgorithm implements CheckAlgorithm {
    @Override
    public double check() {
        return 0;
    }

    @Override
    public double check(List<Double> v1, List<Double> v2){
        //点积
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < Math.min(v1.size(), v2.size()); i++) {
            dotProduct += v1.get(i) * v2.get(i);
            normA += Math.pow(v1.get(i), 2);
            normB += Math.pow(v2.get(i), 2);
        }
        //模积
        double magnitude = (Math.sqrt(normA) * Math.sqrt(normB));
        return magnitude-0<0.0000001 ? 1 : dotProduct / magnitude;
    }
}
