/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choco_solver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author tim-b
 */
public class Decomposition {

    public static void main(String[] args){
        
        Model model = new Model("Decomposition");

        int N = 10;    
        
        IntVar[] vars = model.intVarArray("vars", N, 0, N);
        model.sum(vars, "=", N).post();
        //model.regular(vars, new FiniteAutomaton("[1-5]*[0]*")).post();
        // ER pour forcer les 0 à la fin
        model.regular(vars, new FiniteAutomaton("[1-<"+N+">]+0*")).post();
        
        Solver solver = model.getSolver();
        // affichage des solutions dans l'ordre croissant
        solver.setSearch(Search.inputOrderLBSearch(vars));
        
        int nbSol = 0;
        //Utils.getDomain(vars);
        while(solver.solve()){
            for (int i = 0; i < N; i++) {
                int x = vars[i].getValue();
                if (x != 0) {
                    System.out.print(x + " ");
                }
            }
            System.out.println();
            nbSol++;
            //break;
        }
        System.out.println("#solutions: "+nbSol);       
    }
    // N=5 : 16   solutions
    // N=10 : 512 solutions
}
