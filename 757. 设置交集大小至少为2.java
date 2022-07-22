class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[1] == o2[1] ? -(o1[0] - o2[0]) : o1[1] - o2[1]);
        int res = 2;
        for (int i = 1, l = intervals[0][1] - 1, r = intervals[0][1]; i < intervals.length; ++i) {
            if (r < intervals[i][0]) {
                l = intervals[i][1] - 1;
                r = intervals[i][1];
                res += 2;
            } else if (l < intervals[i][0] && intervals[i][0] <= r) {
                l = r;
                r = intervals[i][1];
                ++res;
            }
        }
        return res;
    }
}