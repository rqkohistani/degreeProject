package prototype_model_v_1.mutators;

public class Mutations {

    public int mutanEquivalent(int a, int b) {
        return a + b;
    }

    public boolean mutanString1(String str1, String str2){
        str1="abc";
        str2="abc";

        return false;
    }

    public int mutan1(int a, int b) {
        return a - b;
    }

    public int mutan2(int a, int b) {
        return a * b;
    }


    public int mutan3(int a, int b) {
        if (b == 0) {
            return b / a;
        }
        return a / b;
    }

    public int mutan4(int a, int b) {
        return a;
    }

    public int mutan5(int a, int b) {
        return b;
    }
}
