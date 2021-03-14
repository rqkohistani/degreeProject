# Bachelor Degree Project VT 2021
# Generating Test Suite from Model Solutions
### A very basic prototype model that shows how the prototype can generate test cases

  ![Prototype model](https://github.com/rqkohistani/degreeProject/blob/main/src/prototype_model_v_1/model_Diagrams_Output/Prototypemodel.JPG)
  ![Prototype model](https://github.com/rqkohistani/degreeProject/blob/main/src/prototype_model_v_1/model_Diagrams_Output/Output1.JPG)
##### Model-solution or the correct version of the program
     ```
      public int add(int a, int b) {
        return a + b;
    }
      ```
      ##### The buggy versions of the program
  ```
  public int mutantEquivalent(int a, int b) {
        return a + b;
    }
  ```
   ```
 public int mutan1(int a, int b) {
        return a - b;
    }
 ```
  ```
    public int mutan2(int a, int b) {
        return a * b;
    }
 ```
  ```

    public int mutan3(int a, int b) {
        if (b == 0) {
            return b / a;
        }
        return a / b;
    }
 ```
  ```
    public int mutan4(int a, int b) {
        return a;
    }
 ```
  ```
    public int mutan5(int a, int b) {
        return b;
    }
     ```
