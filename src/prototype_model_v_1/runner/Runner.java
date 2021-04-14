package prototype_model_v_1.runner;

import prototype_model_v_1.model_solution.ModelSolution;
import prototype_model_v_1.mutators.Mutations;
import prototype_model_v_1.myInput.MyInput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Runner {
    Mutations mutations;
    ModelSolution modelSolution;
    MyInput myInput;


    public Runner() {
        mutations = new Mutations();
        modelSolution = new ModelSolution();
        myInput = new MyInput();
        addAllMutants();
        getPairs();
    }

    private List<Integer> mutants = new ArrayList<>();
    private List<List<Integer>> testCases = new ArrayList<>();
    private Set<List<Integer>> listSet=new HashSet<>();
    private List<Integer> pairs = new ArrayList<>();

    public List<List<Integer>> getTestCases() {
        return testCases;
    }

    public Set<List<Integer>> getListSet() {
        return listSet;
    }

    private void addAllMutants() {
//        mutants.add(mutations.mutanEquivalent(myInput.getA(),myInput.getB()));
        mutants.add(modelSolution.add(myInput.getA(), myInput.getB()));
        mutants.add(mutations.mutan1(myInput.getA(), myInput.getB()));
        mutants.add(mutations.mutan2(myInput.getA(), myInput.getB()));
        mutants.add(mutations.mutan3(myInput.getA(), myInput.getB()));
        mutants.add(mutations.mutan4(myInput.getA(), myInput.getB()));
        mutants.add(mutations.mutan5(myInput.getA(), myInput.getB()));
        mutants.add(mutations.mutanEquivalent(myInput.getA(), myInput.getB()));
    }

    private void getPairs() {
        int start = 1;
        int modelSolutionPositionInmutantsArray = 0;
        int numberOfEquivalenMutantsFound = 0;
        for (int i = 0; i < 10; i++) {


            while (start < mutants.size()) {
//                a =5;
//                b = 3;
//                ms=8    m1 = 2
                if (mutants.get(modelSolutionPositionInmutantsArray) != mutants.get(start)) {
                    pairs.add(myInput.getA()); //
                    pairs.add(myInput.getB());
//                    pairs[5,3]
                    testCases.add(pairs);
//                    [[5,3]]
                    listSet.add(pairs);
                    pairs = new ArrayList<>();
                    resetValues();


//            after even one possible test case we stop iterating more
//                    at this level equivalent mutants are the big concern
                    break;
                } else {
                    //do nothing or come up with better approach
//                numberOfEquivalenMutantsFound++;
//                System.out.println("else statement");
                }
                start++;
            }
        }
    }

    public void resetValues() {
        mutations = new Mutations();
        // modelSolution= new ModelSolution();//once you created the model solution so you dont to create the object
        myInput = new MyInput();
        addAllMutants();
    }


}
