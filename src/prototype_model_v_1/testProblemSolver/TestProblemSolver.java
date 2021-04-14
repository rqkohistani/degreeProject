package prototype_model_v_1.testProblemSolver;

import prototype_model_v_1.TestCases.TestCases;
import prototype_model_v_1.runner.Runner;

public class TestProblemSolver {
    Runner runner;
    TestCases testCases;
    public TestProblemSolver(){
        testCases=new TestCases();
        testCases.testCasesNotMinimized();
       runner=new Runner();
//        testCasesMinimized();
    }

    public void testSuite(){
        String testSuite="public int add(int a, int b){\n"
                +"assertEqual("+runner.getTestCases().get(0).get(0)+","+runner.getTestCases().get(0).get(1)+")";
        System.out.println(testSuite);
    }

    public void testCasesMinimized(){
        System.err.println("Minimized test cases 'not yet implemented'");
        testSuite();

    }
}
