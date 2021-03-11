package review_one.iteration_v_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class C_BiggerThan_10 {
    static Random random=new Random();
        static int a=random.nextInt(20);
//    static int a=10 ;

    public static void main(String[] args) {
        List<Integer> tCase=new ArrayList<>();
        ArrayList<Boolean> mutants = new ArrayList<>();
        List<Integer> equilentMutants=new ArrayList<>();
        boolean modelSolution = biggerthan_10();
        mutants.add(biggerthan_10m1());
        mutants.add(biggerthan_10m2());
        mutants.add(biggerthan_10m3());
        int start=0;
        while (start<mutants.size()){
            if(modelSolution == mutants.get(start)){
                equilentMutants.add(a);
            }
            else if (modelSolution != mutants.get(start)){
                tCase.add(a);
//                whenever we find a test case we skip to iterate more
                break;
            }
            else {
                //do nothing
            }
            start++;
        }


        System.out.println("mutants: "+mutants);
        System.out.println("test case: "+tCase);
        System.out.println("equivalent mutants: "+equilentMutants);
    }
//Model solution
    public static boolean biggerthan_10(){
//        int a=10;
        return a>9;
    }
//    Mutants
    public static boolean biggerthan_10m1(){
//        int a=10;
        return a>=9;
    }
    public static boolean biggerthan_10m2(){
//        int a=10;
        return a<9;
    }
    public static boolean biggerthan_10m3(){
//        int a=10;
        return a<=9;
    }
}
