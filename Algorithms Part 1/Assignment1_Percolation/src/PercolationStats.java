import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double[] openSites;
    static Integer Size;

    public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
        Size = n;
        openSites = new double[trials];
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < trials; i ++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                PercolationVisualizer.draw(perc, n);
                StdDraw.show();
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
//                System.out.println(x + ", " + y);
                if (!perc.isOpen(x, y)) {
                    perc.open(x, y);
                }
//                System.out.println(perc.percolates());

            }
            double number = n;
//            System.out.println(perc.numberOfOpenSites() / (number * number));
            openSites[i] = perc.numberOfOpenSites() / (number * number);
        }
        System.out.println("Size                    = " + n);
        System.out.println("Mean                    = " + mean());
        System.out.println("Stddev                  = " + stddev());
        System.out.println("95% confidence interval = [" + confidenceLo() + ", " + confidenceHi() + "]");
    }
    public double mean() {                         // sample mean of percolation threshold
        return StdStats.mean(openSites);
    }
    public double stddev() {                       // sample standard deviation of percolation threshold
        return StdStats.stddev(openSites);
    }
    public double confidenceLo() {                 // low  endpoint of 95% confidence interval
        return mean() - stddev() * 1.96 / Math.sqrt(openSites.length);
    }
    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        return mean() + stddev() * 1.96 / Math.sqrt(openSites.length);
    }

    public static void main(String[] args) {       // test client (described below)
        int n = 10;
        int m = 100;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        }
        PercolationStats pers = new PercolationStats(n, m);
    }
}