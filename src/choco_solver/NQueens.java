package choco_solver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;

/*
 * Nqueens problem with SIZE*SIZE 
*/
public class NQueens {

    private static final int SIZE = 8;
    
    public static void main(String[] args) throws ContradictionException {

        Model model = new Model("NQueens");
    
        IntVar[] R = model.intVarArray("R", SIZE, 0, SIZE-1);
        //model.allDifferent(R).post();
        
        // row i, column j
        // set constraints : |Ri-Rj| != j-i for all j > i
        for (int i = 0; i < R.length; i++) {
            for (int j = i+1; j < R.length; j++) {
                model.arithm(R[i], "!=", R[j]).post();    // allDIfferent
                model.arithm(R[i], "!=", R[j], "+", j-i).post();
                model.arithm(R[i], "!=", R[j], "-", j-i).post();
            }
        }
        
        Solver solver = model.getSolver();
        //solver.propagate(); // to see the domains of var   
        solver.showShortStatisticsOnShutdown();
        Utils.displayDomains(R);
        
        Solution sol;
        int nbSol = 0;
        while((sol = solver.findSolution()) != null){
            System.out.println(sol);
            nbSol++;
        }
        System.out.println("#nb sols : "+nbSol);
    }

// SIZE = 8 => 92 solutions  
}
