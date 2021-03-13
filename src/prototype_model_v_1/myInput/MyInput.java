package prototype_model_v_1.myInput;

import java.util.Random;

public class MyInput {
    Random random=new Random();
    private     int a = random.nextInt(100);
    private int b = random.nextInt(100);
//    int a = 4;
//    int b = 2;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
