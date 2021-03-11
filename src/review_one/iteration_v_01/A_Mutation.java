package review_one.iteration_v_01;

import java.util.Random;

public class A_Mutation {
    public static void main(String[] args) {
        Random random =new Random();
        int i=0;
        while (i<20){
            int fuz=random.nextInt(11);
            //boolean expected=correctBiggerThan10(fuz);//5
//            boolean mutant=mutant1tBiggerThan10(fuz);
//            boolean mutant12=mutant2tBiggerThan10(fuz);
            boolean expected=correctBiggerThan10(i);//5
            boolean mutant=mutant1tBiggerThan10(i);
            boolean mutant12=mutant2tBiggerThan10(i);
            if (expected!=mutant12){
//         if (expected!=mutant || expected!=mutant12){
//                System.out.println(i+" fuz value is good test input "+fuz);
                System.out.println(i+" fuz value is good test input "+i);
            }
            else
            {
//                System.out.println("equivalent mutant"+fuz);
                System.out.println("equivalent mutant "+i);
            }
            i++;
        }
    }
    private static boolean correctBiggerThan10(int n){
        return n>10;
    }
//    For this methods there is only one number different when comparing MS vs M1
    // 10 > 10 F   10>=10 T => rest the of the cases are equal meaning they are equivalent mutants
    private static boolean mutant1tBiggerThan10(int n){
        return n>=10;
    }
//    This method will generate 0-9 and 11-19 different cases when comparing MS vs M2
//    10>10 F  10<10 F same value considered ast equivalent mutant
    private static boolean mutant2tBiggerThan10(int n){
        return n<10;
    }
}
