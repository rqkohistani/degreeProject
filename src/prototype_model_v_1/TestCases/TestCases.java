package prototype_model_v_1.TestCases;

import prototype_model_v_1.runner.Runner;

public class TestCases {
    Runner runner;
    public TestCases(){
        runner=new Runner();

    }

    public void testCasesNotMinimized(){
        System.out.println( "\r\nmutations forms");
        System.out.println("a-b, a*b, a/b, a, b, a+b \r\n");
        System.out.println("All possible test cases'not minimized' ");

        System.out.println(runner.getTestCases());
    }


}
