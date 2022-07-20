class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int idx = ((i * n + j) + k) % (m * n);
                g[idx / n][idx % n] = grid[i][j];
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            Collections.addAll(res,  Arrays.stream(g[i]).boxed().collect(Collectors.toList()));
        }
        return res;
    }
}