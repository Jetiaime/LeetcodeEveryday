class Solution {
    static final int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int containVirus(int[][] isInfected) {
        /*
            for (int[] t : isInfected) {
                System.out.println(Arrays.toString(t));
            }
         */
        List<List<int[]>>[] arrBound = getBounds(isInfected);
        List<List<int[]>> bounds = arrBound[0];
        List<List<int[]>> grids = arrBound[1];
        if (bounds.size() == 0) {
            return 0;
        }

        /*
            for (List<int[]> bound : bounds) {
                for (int[] cur : bound) {
                    System.out.print("(" + cur[0] + ", " + cur[1] + ")\t");
                }
                System.out.println("");
            }
            System.out.println(bounds.size());
        */

        int n = isInfected.length;
        int m = isInfected[0].length;
        int _mx = 0, idx = 0;
        for (int i = 0, _tmp, l = bounds.size(); i < l; ++i) {
            _tmp = 0;
            boolean[][] vis = new boolean[n][m];
            for (int[] cur : bounds.get(i)) {
                for (int[] d : dir) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (!(0 <= x && x < n && 0 <= y && y < m)) {
                        continue;
                    }
                    if (!vis[x][y] && isInfected[x][y] == 0) {
                        ++_tmp;
                        vis[x][y] = true;
                    }
                }
            }
            if (_tmp > _mx) {
                _mx = _tmp;
                idx = i;
            }
        }
        // System.out.println("影响了: " + _mx);

        _mx = 0;
        for (int[] cur : bounds.get(idx)) {
            for (int[] d : dir) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                if (!(0 <= x && x < n && 0 <= y && y < m)) {
                    continue;
                }
                if (isInfected[x][y] == 0) {
                    ++_mx;
                }
            }
            isInfected[cur[0]][cur[1]] = -1;
            // System.out.println(cur[0] + ", " + cur[1] + " : " + _mx);
        }

        for (int i = 0, l = bounds.size(); i < l; ++i) {
            if (i == idx) {
                continue;
            }
            for (int[] cur : bounds.get(i)) {
                for (int[] d : dir) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (!(0 <= x && x < n && 0 <= y && y < m)) {
                        continue;
                    } else if (isInfected[x][y] == 0) {
                        isInfected[x][y] = 1;
                    }
                }
            }
        }

        // System.out.println("res = " + _mx);

        return _mx + containVirus(isInfected);
    }

    private List<List<int[]>>[] getBounds(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        List<List<int[]>>[] res = new ArrayList[2];
        res[0] = new ArrayList<>();
        res[1] = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (vis[i][j] || grid[i][j] != 1) {
                    continue;
                }
                List<int[]> b = new ArrayList<>();
                List<int[]> g = new ArrayList<>();
                q.add(new int[] {i, j});
                vis[i][j] = true;
                for (int[] cur; !q.isEmpty();) {
                    cur = q.poll();
                    g.add(cur);
                    boolean isBound = false;
                    for (int d[] : dir) {
                        int x = cur[0] + d[0];
                        int y = cur[1] + d[1];
                        if (!(0 <= x && x < n && 0 <= y && y < m) || vis[x][y] || grid[x][y] == -1) {
                            continue;
                        }
                        if (grid[x][y] == 0) {
                            if (!isBound) {
                                b.add(cur);
                                isBound = true;
                            }
                        } else {
                            q.add(new int[] {x, y});
                            vis[x][y] = true;
                        }
                    }
                }
                if (b.size() > 0) {
                    res[0].add(b);
                    res[1].add(g);
                }
            }
        }
        return res;
    }
}