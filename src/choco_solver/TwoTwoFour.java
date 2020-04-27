/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choco_solver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class TwoTwoFour {

    public static void main(String[] args) {
        
        Model model = new Model("TWO+TWO=FOUR");
        IntVar T = model.intVar("T", 1, 9);
        IntVar W = model.intVar("W", 0, 9);
        IntVar O = model.intVar("O", 0, 9);
        IntVar F = model.intVar("F", 1, 9);
        IntVar U = model.intVar("U", 0, 9);
        IntVar R = model.intVar("R", 0, 9);
        model.allDifferent(T, W, O, F, U, R).post();
 
        // First choco exemple
        IntVar[] vars = new IntVar[]{
            T, W, O,
            T, W, O,
            F, O, U, R};
        int[] coeffs = new int[]{
            100, 10, 1,
            100, 10, 1,
            -1000,-100,-10, -1};
            model.scalar(vars, coeffs, "=", 0).post();
        Solver solver = model.getSolver();
        System.out.println(solver.findSolution());
 
 
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
