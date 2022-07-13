import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < asteroids.length; ++i) {
            if (asteroids[i] < 0) {
                for (; !st.isEmpty() && 0 < st.peek() && st.peek() < -asteroids[i]; st.pop())
                    ;
                if (!st.isEmpty() && st.peek() > -asteroids[i]) {
                    continue;
                }
                if (!st.isEmpty() && st.peek() == -asteroids[i]) {
                    st.pop();
                    continue;
                }
                st.push(asteroids[i]);
            } else {
                st.push(asteroids[i]);
            }
        }
        System.out.println(st);
        int[] res = new int[st.size()];
        for (int i = st.size() - 1; !st.isEmpty(); --i) {
            res[i] = st.pop();
        }
        return res;
    }
}