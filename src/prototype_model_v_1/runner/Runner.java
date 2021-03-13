package prototype_model_v_1.runner;

import prototype_model_v_1.model_solution.ModelSolution;
import prototype_model_v_1.mutators.Mutations;
import prototype_model_v_1.myInput.MyInput;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    Mutations mutations;
    ModelSolution modelSolution;
    MyInput myInput;


    public Runner(){
        mutations =new Mutations();
        modelSolution= new ModelSolution();
        myInput=new MyInput();
        addAllMutants();
        getPairs();
    }
    private List<Integer> mutants = new ArrayList<>();
    private  List<List<Integer>> testCases = new ArrayList<>();
    private List<Integer> pairs = new ArrayList<>();

    public List<List<Integer>> getTestCases() {
        return testCases;
    }

    private void addAllMutants(){
        mutants.add(modelSolution.add(myInput.getA(),myInput.getB()));
        mutants.add(mutations.mutan1(myInput.getA(),myInput.getB()));
        mutants.add(mutations.mutan2(myInput.getA(),myInput.getB()));
        mutants.add(mutations.mutan3(myInput.getA(),myInput.getB()));
        mutants.add(mutations.mutan4(myInput.getA(),myInput.getB()));
        mutants.add(mutations.mutan5(myInput.getA(),myInput.getB()));
        mutants.add(mutations.mutanEquivalent(myInput.getA(),myInput.getB()));
        System.out.println("mutations forms");
        System.out.println("a-b, a*b, a/b, a, b, a+b");
        System.out.println(mutants);

    }

    private void getPairs(){
        int start=1;
        int modelSolutionPositionInmutantsArray=0;
        int numberOfEquivalenMutantsFound=0;
        while (start<mutants.size()){
            if(mutants.get(modelSolutionPositionInmutantsArray) !=mutants.get(start)) {
                pairs.add(myInput.getA());
                pairs.add(myInput.getB());
                testCases.add(pairs);
//            after even one possible test case we stop iterating more
                break;
            }
            else {
                //do nothing
                numberOfEquivalenMutantsFound++;
//                System.out.println("else statement");
            }
            start++;

        }
//        System.out.println("Number of equivalent mutants "+numberOfEquivalenMutantsFound);
    }


}
