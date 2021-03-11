package review_one.iteration_v_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddTwoInteger {
    static Random random = new Random();
    static int a = random.nextInt(100);
    static int b = random.nextInt(100);

    public static void main(String[] args) {
        System.err.println("public int add(int a, int b{" +
                "return a + b; " +
                "}");
        System.out.println("Model Solution: "+a+" + "+b+ " ==> "+(a+b));
        System.out.println("a => " + a + " b=> " + b);
        List<List<Integer>> testCases = new ArrayList<>();
        List<Integer> pairs = new ArrayList<>();
        List<Integer> mutants = new ArrayList<>();
        int modelSolution = addMS();
        mutants.add(mutan1());
        mutants.add(mutan2());
        mutants.add(mutan3());
        mutants.add(mutan4());
        mutants.add(mutan5());
        mutants.add(mutanEquivalent());
        System.out.println("a-b, a*b, a/b, a, b, a+b");
        System.out.println(mutants);

        int start=0;
        while (start<mutants.size()){
            if(modelSolution != mutants.get(start)) {
                pairs.add(a);
                pairs.add(b);
                testCases.add(pairs);
//            after even one possible test case we stop iterating more
                break;
            }
            else {
                //do nothing
                System.out.println("else statement");
            }
            start++;
        }

        System.out.println("Test Cases: " + testCases);



    }
//    Model solution
    private static int addMS() {
        return a + b;
    }
//    equivalent mutant
    private static int mutanEquivalent() {
        return a + b;
    }
    private static int mutan1() {
        return a - b;
    }

    private static int mutan2() {
        return a * b;
    }


    private static int mutan3() {
        if (b == 0) {
            return b / a;
        }
        return a / b;
    }

    private static int mutan4() {
        return a;
    }

    private static int mutan5() {
        return b;
    }


}