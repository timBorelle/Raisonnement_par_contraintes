/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choco_solver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author tim-b
 */
public class VendingMachine {

    public static void main(String[] args){
        
        Model model = new Model("VendingMachine");

        //Random random = new Random();
        int A = 200;    // money inserted : 2.00€
        int B = 135;    // product value : 1.35€
        int N = 200;
               
	IntVar P200 = model.intVar("P200", 0, N);   // 2€ coin
        IntVar P100 = model.intVar("P100", 0, N);   // 1€ coin
        IntVar P50 = model.intVar("P50", 0, N);     // 0.5€ coin    
        IntVar P20 = model.intVar("P20", 0, N);     // 0.2€ coin      
        IntVar P10 = model.intVar("P10", 0, N);     // 0.1€ coin    
        IntVar P5 = model.intVar("P5", 0, N);       // 0.05€ coin
        
        IntVar[] vars = new IntVar[]{P200, P100, P50, P20, P10, P5};
        int[] coeffs = new int[]{200, 100, 50, 20, 10, 5};
        //IntVar NC = model.intVar("Nb coins", 0, A);
        //model.sum(N, "=", NC).post();
        model.scalar(vars, coeffs, "=", A-B).post();
        
        Solver solver = model.getSolver();
        
        Solution sol;
        int nbSol = 0;
        //Utils.getDomain(vars);
        while((sol = solver.findSolution()) != null){
            System.out.println(sol);
            nbSol++;
            //break;
        }
        System.out.println(nbSol+" solutions.");       
    }    
}
