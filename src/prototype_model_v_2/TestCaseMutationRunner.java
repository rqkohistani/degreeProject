package prototype_model_v_2;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class TestCaseMutationRunner {

    public TestCaseMutationRunner() {

        try {
            Calculator calc = new Calculator();
            Class _class = CalculatorMutations.class;

            CalculatorMutations calculatorMutations = new CalculatorMutations();

            Method[] methods = _class.getDeclaredMethods();

            Random random = new Random(System.currentTimeMillis());

//            TestCaseWriter unminimized = new TestCaseWriter(new File(System.getProperty("user.dir") + File.separator + "unminimized.log"));
            prototype_model_v_2.TestCaseWriter unminimized = new prototype_model_v_2.TestCaseWriter(new File(System.getProperty("user.dir") + File.separator +"\\src\\prototype_model_v_2\\unminimized.txt"));
//            TestCaseWriter minimized = new TestCaseWriter(new File(System.getProperty("user.dir") + File.separator + "minimized.log"));
            prototype_model_v_2.TestCaseWriter minimized = new prototype_model_v_2.TestCaseWriter(new File(System.getProperty("user.dir")+ File.separator +"\\src\\prototype_model_v_2\\minimized.txt"));
            unminimized.addData("MODEL SOLUTION,MUTATION,INPUT 1,INPUT 2,EXPECTED RESULT,ACTUAL RESULT\n");
            minimized.addData("MODEL SOLUTION,MUTATION,INPUT 1,INPUT 2,EXPECTED RESULT,ACTUAL RESULT,PASS\n");

            Set<Integer> duplicateTestCases = new HashSet<>();
//            for (int x = 0; x < 1_000_000; x++) {
            for (int x = 0; x < 1_000; x++) {

//                Integer input1 = random.nextInt(2_000_000);
//                Integer input2 = random.nextInt(2_000_000);
                Integer input1 = random.nextInt(10);
                Integer input2 = random.nextInt(10);
                for (Method xx : methods) {
                    if (xx.getName().startsWith("mutant")) {

                        int calcResult = calc.add(input1, input2);
                        int mutantResult = (Integer) xx.invoke(calculatorMutations, input1, input2);

                        if (calcResult != mutantResult) {
                            MutationModel model = new MutationModel();
                            model.setMutation(xx.getName());
                            model.setExpectedOutput(calcResult);
                            model.setActualOutput(mutantResult);
                            model.setInput1(input1);
                            model.setInput2(input2);
                            model.setModelSolution("ADD");
                            model.setPass(calcResult != mutantResult);
                            unminimized.addData(model.getModelSolution() + "," + model.getMutation() + ","
                                    + model.getInput1() + "," + model.getInput2() + ","
                                    + model.getExpectedOutput() + "," + model.getActualOutput() + "\n");
                            if (!duplicateTestCases.contains(model.hashCode())) {
                                duplicateTestCases.add(model.hashCode());
                                minimized.addData(model.getModelSolution() + "," + model.getMutation() + ","
                                        + model.getInput1() + "," + model.getInput2() + ","
                                        + model.getExpectedOutput() + "," + model.getActualOutput() + "\n");
                            }
                        }
                    }
                }
            }
            minimized.closeWriter();
            unminimized.closeWriter();
            System.out.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestCaseMutationRunner();
        System.out.println("The results is outputted in txt files");
    }
}

class TestCaseWriter {

    private FileWriter writer;
    private String path;

    public TestCaseWriter(File file) {
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            writer = new FileWriter(file, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addData(String data) {
        try {
            writer.write(data);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public FileWriter getWriter() {
        return writer;
    }

    public void setWriter(FileWriter writer) {
        this.writer = writer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void closeWriter() {
        try {
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CalculatorMutations {

    private final static int NEGATIVE_BIT_FLIP = 0xffffffff;

    /*
     *Boolean Constants
     */
    public int mutant1(int input1, int input2) {
        return Boolean.TRUE
                ? input1 + input2 == 0 ? -1 : input1 + input2 == -1 ? 0 : -1 : 0;

        //input1 + input2 == -1 ? 0 : -1;
    }

    /*
     *Boolean Operators &&
     */
    public int mutant2(int input1, int input2) {
        if (input1 < 0 && input2 < 0) {
            return Math.abs(input1 + input2);
        } else {
            return mutant1(input1, input2);
        }
    }

    /*
     *Boolean Operators &&, ||
     */
    public int mutant3(int input1, int input2) {
        if ((input1 < 0 || input2 < 0) && (input1 != input2)) {
            return input1 + input2 == 0 ? -1 : (input1 + input2) * -1;
        } else {
            return mutant1(input1, input2);
        }
    }

    /*
     *Relational Operators ==,<=,>=,!=
     */
    public int mutant4(int input1, int input2) {
        if ((input1 != 0 && input2 != 0)
                && (input1 == input2 || (input1 <= input2) || (input2 <= input1)) && (input1 + input2 != 0)) {
            return 0;
        }
        return mutant1(input1, input2);
    }

    /*
     *Relational Operators <, >
     */
    public int mutant5(int input1, int input2) {
        return (input1 < 0 || input2 < 0 || input1 > 0 || input2 > 0)
                ? mutant1(input1, input2) : -1;
    }

    /*
     *Incr/Decr Operators ++
     */
    public int mutant6(int input1, int input2) {
        return ++input1 + ++input2;
    }

    /*
     *Incr/Decr Operators --
     */
    public int mutant7(int input1, int input2) {
        return --input1 + --input2;
    }

    /*
     *Arithmetic Operators Add +
     */
    public int mutant8(int input1, int input2) {
        return (input1 == 0 && input2 == 0) || input1 + input2 == 0
                ? -1 : ((((input1 + input2) ^ NEGATIVE_BIT_FLIP) + 1));
    }

    /*
     *Arithmetic Operators Sub -
     */
    public int mutant9(int input1, int input2) {
        return (input1 == 0 && input2 == 0) || Math.abs(input1) - Math.abs(input2) == 0
                ? -1 : 0;
    }

    /*
     *Arithmetic Operators Mul *
     */
//    -1, 0
    public int mutant10(int input1, int input2) {

        return input1 != 0 || input2 != 0 ? ((input1 == 0 && input2 != 0) || (input2 == 0 && input1 != 0)
                || (Math.abs(input1) + Math.abs(input2)) == (Math.abs(input1) * Math.abs(input2))
                ? 0 : (Math.abs(input1) * Math.abs(input2))) : -1;

//        return (input1 == 0 && input2 != 0) || (input2 == 0 && input1 != 0)
//                || (input1 + input2) != (input1 * input2) ? 0 : (input1 * input2);
    }

    /*
     *Arithmetic Operators Div /
     */
    public int mutant11(int input1, int input2) {
        return input1 + input2 == 0 ? -1 : input1 != 0 && input2 == 0 ? 0 : input1 / input2;
    }

    /*
     *Arithmetic Assignment Operators +=
     */
    public int mutant12(int input1, int input2) {
        int result = 0;

        result += input1;
        result += input2;
        result += result;

        return result == 0 ? -1 : result;
    }

    /*
     *Arithmetic Assignment Operators -=
     */
    // 0 -1
    public int mutant13(int input1, int input2) {
        int result = 0;

        result -= input1;
        result -= input2;
        result -= result;

        return result == 0 ? -1 : result;
    }

    /*
     *Arithmetic Assignment Operators *=
     */
    public int mutant14(int input1, int input2) {
        int result = 1;

        result *= input1;
        result *= input2;
        result *= result;

        return result == 0 ? (input1 == 0 && input2 == 0) ? -1 : input1 * input2 : result;
    }

    /*
     *Arithmetic Assignment Operators /=
     */
    public int mutant15(int input1, int input2) {
        try {
            int result = input1;

            result /= input2;

            return result == 0
                    ? (input1 == 0 && input2 == 0) ? -1 : input1 / input2 : result;
        } catch (ArithmeticException e) {
            return input1 != 0 ? 0 : -1;
        }
    }

    /*
     *Binary Bit Assignment Operators &=
     */
    public int mutant16(int input1, int input2) {

        int result = input1;
        result &= input2;
        return (input1 == 0 && input2 == 0) ? -1 : result == 0 ? 0 : result;
    }

    /*
     *Binary Bit Assignment Operators |=
     */
    public int mutant17(int input1, int input2) {

        int result = input1;
        result |= input2 ^ NEGATIVE_BIT_FLIP;
        result++;

        return (input1 == 0 && input2 == 0) ? -1 : result;
    }

    /*
     *Binary Bit Assignment Operators ^=
     */
    public int mutant18(int input1, int input2) {

        int result = input1;
        result ^= input2 ^ NEGATIVE_BIT_FLIP;
        return (input1 == 0 && input2 == 0) ? -1 : result;
    }

    /*
     *Binary Bit Assignment Operators %=
     */
    public int mutant19(int input1, int input2) {
        try {
            int x = Math.abs(input1);
            int y = Math.abs(input2);

            int result = x;
            result %= y;

            return (input1 == 0 && input2 == 0) ? -1 : (result == input1 + input2) ? ++result : result;
        } catch (ArithmeticException e) {
            return input1 != 0 ? 0 : -1;
        }
    }

    /*
     *Binary Bit Assignment Operators <<=
     */
    public int mutant20(int input1, int input2) {

        int x = input1 == 0 ? 1 : input1;
        x <<= Math.min(input1, 16);

        int y = input2 == 0 ? 1 : input2;
        y <<= Math.min(input2, 16);

        return (input1 == 0 && input2 == 0) ? -1 : x + y;
    }

    /*
     *Binary Bit Assignment Operators >>=
     */
    public int mutant21(int input1, int input2) {
        int x = input1;
        int y = input2;

        x = x == 0 ? 1 : Math.abs(x);
        y = y == 0 ? 1 : Math.abs(y);

        int result = Integer.MAX_VALUE;

        result >>= x;
        result >>= y;

        return (input1 == 0 && input2 == 0) ? -1 : result;

    }
}

class Calculator {

    public int add(int x, int y) {
        return x + y;
    }
}

class MutationModel {

    private int expectedOutput;
    private int actualOutput;
    private int input1;
    private int input2;
    private String mutation;
    private String modelSolution;
    private boolean pass;

    public int getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(int expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public int getActualOutput() {
        return actualOutput;
    }

    public void setActualOutput(int actualOutput) {
        this.actualOutput = actualOutput;
    }

    public int getInput1() {
        return input1;
    }

    public void setInput1(int input1) {
        this.input1 = input1;
    }

    public int getInput2() {
        return input2;
    }

    public void setInput2(int input2) {
        this.input2 = input2;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getModelSolution() {
        return modelSolution;
    }

    public void setModelSolution(String modelSolution) {
        this.modelSolution = modelSolution;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.expectedOutput;
        hash = 31 * hash + this.actualOutput;
        hash = 31 * hash + this.input1;
        hash = 31 * hash + this.input2;
        hash = 31 * hash + Objects.hashCode(this.mutation);
        hash = 31 * hash + Objects.hashCode(this.modelSolution);
        hash = 31 * hash + (this.pass ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MutationModel other = (MutationModel) obj;
        if (this.expectedOutput != other.expectedOutput) {
            return false;
        }
        if (this.actualOutput != other.actualOutput) {
            return false;
        }
        if (this.input1 != other.input1) {
            return false;
        }
        if (this.input2 != other.input2) {
            return false;
        }
        if (this.pass != other.pass) {
            return false;
        }
        if (!Objects.equals(this.mutation, other.mutation)) {
            return false;
        }
        if (!Objects.equals(this.modelSolution, other.modelSolution)) {
            return false;
        }
        return true;
    }

}
