
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid; // grid[i][j] = site at row i, column j
    private final int gridSide; // gridSide of the grid
    private final int top = 0; // virtual top
    private final int bottom; // virtual bottom
    private final WeightedQuickUnionUF uf; // a WeightedQuickUnionUF instance

    /**
     * Create a n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        gridSide = n;
        grid = new boolean[n][n];
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    /**
     * Open site (row i, column j) if it is not open already, also union sites
     */
    public void open(int i, int j) {
        grid[i - 1][j - 1] = true;

        if (i == 1) { // union virtual top
            uf.union(getIndex(i, j), top);
        }
        if (i == gridSide) { // union virtual bottom
            uf.union(getIndex(i, j), bottom);
        }

        // union possible neighbor(s)
        if (i > 1 && isOpen(i - 1, j)) {
            uf.union(getIndex(i, j), getIndex(i - 1, j));
        }
        if (i < gridSide && isOpen(i + 1, j)) {
            uf.union(getIndex(i, j), getIndex(i + 1, j));
        }
        if (j > 1 && isOpen(i, j - 1)) {
            uf.union(getIndex(i, j), getIndex(i, j - 1));
        }
        if (j < gridSide && isOpen(i, j + 1)) {
            uf.union(getIndex(i, j), getIndex(i, j + 1));
        }
    }
    /**
     * Count if site (row i, column j) open?
     *
     * @return number of open sites
     */

    public     int numberOfOpenSites(){
        int open=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]){
                    open++;
                }
            }
        }
        return open;
    }
    /**
     * Is site (row i, column j) open?
     *
     * @return true if site (row i, column j) is open; false otherwise
     * @throws java.lang.IndexOutofBoundsException
     *             unless both 1<= i <= n and 1 <= j <= n
     */
    public boolean isOpen(int i, int j) {
        return grid[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     *
     * @return true if site (row i, column j) is full; false otherwise
     * @throws java.lang.IndexOutofBoundsException
     *             unless both 1<= i <= n and 1 <= j <= n
     */
    public boolean isFull(int i, int j) {
        if (i <= 0 || i > gridSide || j <= 0 || j > gridSide) throw new IndexOutOfBoundsException();
        return uf.connected(getIndex(i, j), top);
    }

    /**
     * Does the system percolate?
     *
     * @return true of the system percolates; false otherwise
     */
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    /**
     * Return the index at site(i, j)
     *
     * @return index at site(i, j)
     */
    private int getIndex(int i, int j) {
        return gridSide * (i - 1) + j;
    }

    public static void main(String[] args) {

    }

}
