/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choco_solver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author tim-b
 */
public class SendMoreMoney {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Model model = new Model("SEND+MORE=MONEY");
        IntVar S = model.intVar("S", 1, 9);
        IntVar E = model.intVar("E", 0, 9);
        IntVar N = model.intVar("N", 0, 9);
        IntVar D = model.intVar("D", 0, 9);
        IntVar M = model.intVar("M", 1, 9);
        IntVar O = model.intVar("U", 0, 9);
        IntVar R = model.intVar("R", 0, 9);
        IntVar Y = model.intVar("Y", 0, 9);
        model.allDifferent(S, E, N, D, M, O, R, Y).post();
 
        // First choco exemple
        IntVar[] vars = new IntVar[]{
            S, E, N, D,
            M, O, R, E,
            M, O, N, E, Y};
        int[] coeffs = new int[]{
            1000, 100, 10, 1,
            1000, 100, 10, 1,
            -10000, -1000,-100,-10, -1};
            model.scalar(vars, coeffs, "=", 0).post();
            
        Solver solver = model.getSolver();
        System.out.println(solver.findSolution());
        solver.showStatistics();
        //solver.showSolutions();
        //while(solver.solve());
 
        // one solution
        //Solution solution =
                
        // Create a new model
//        Model model = new Model();
//
//        // create new var
//        IntVar x = model.intVar("name", min, max);
//
//        // Create and post (activate) the constraints
//        model.<constraint>.post();
//
//        // create solver
//        Solver solver = model.getSolver();
//
//        // search solution
//        solver.solve();
//        // or
//        solver.findSolution();
    }
    
}
