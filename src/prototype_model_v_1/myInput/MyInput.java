package prototype_model_v_1.myInput;

public class MyInput {
//    Random random=new Random();
//    private     int a = random.nextInt(100);
//    private int b = random.nextInt(100);
    private int a=(int)(Math.random() * 2)+1;
    private int b=(int)(Math.random() * 2)+1;
//    int a = 4;
//    int b = 2;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
