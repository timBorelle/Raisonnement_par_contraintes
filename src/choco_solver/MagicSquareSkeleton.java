package choco_solver;

import common.MagicSquareAbstract;
import java.util.List;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

/**
 * Magic Square Skeleton
 * 
 * n=7 : 5s
 * n=8 : much time
 */
public class MagicSquareSkeleton extends MagicSquareAbstract {

    public static void main(String... args) {
        int n = 7;
        MagicSquareAbstract m = new MagicSquareSkeleton(n);
        if (m.solveMagicSquare()) {
            m.printMagicSquare();
        }
    }

    public MagicSquareSkeleton(int n){
        super(n);
    }
    
    @Override
    public boolean solveMagicSquare() {
        Model model = new Model("MagicSquare " + n);
        IntVar[][] v = model.intVarMatrix("v", n, n, 1, n2);
        
        IntVar[] all = new IntVar[n2];
        IntVar[] d1 = new IntVar[n];    // diagonale 1
        IntVar[] d2 = new IntVar[n];    // diagonale 2
        
        //model.allDifferent(all).post();
        for (int i = 0; i < n; i++){
            IntVar[] l = new IntVar[n];     // line
            IntVar[] col = new IntVar[n];   // column
            for (int j = 0; j < n; j++){
                all[i * n + j] = v[i][j];
                l[j] = v[i][j];
                col[j] = v[j][i];
            }
            model.sum(l, "=", sum).post();  // contraintes sur les lignes
            model.sum(col, "=", sum).post();  // contraintes sur les colonnes
            d1[i] = v[i][i];
            d2[i] = v[i][n - i - 1];
        }
        model.allDifferent(all).post();
        model.sum(d1, "=", sum).post();     // contraintes sur diagonale1
        model.sum(d2, "=", sum).post();
        
        Solver solver = model.getSolver();
        Solution solution = solver.findSolution();
        if (solution == null)
            return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                t[i][j] = v[i][j].getValue();
        return true;
    }
}
