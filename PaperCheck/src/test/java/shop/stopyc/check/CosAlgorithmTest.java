package shop.stopyc.check;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class CosAlgorithmTest {

    Random r;
    CheckAlgorithm checkAlgorithm;

    @Before
    public void start(){
        r = new Random(1000L);
        checkAlgorithm = new CosAlgorithm();
    }

    @Test
    public void check1() {
        checkAlgorithm.check();
    }

    @Test
    public void check2() {
        for(int o=0;o<10;o++) {
            List<Double> v1 = new ArrayList<>();
            List<Double> v2 = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                v1.add(r.nextDouble());
                v2.add(r.nextDouble());
            }
            System.out.println(checkAlgorithm.check(v1, v2));
        }
    }
}