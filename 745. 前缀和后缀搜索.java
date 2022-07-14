import java.util.HashMap;
import java.util.Map;

class WordFilter {

    Map<String, Integer> distionary = new HashMap<>();

    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            int n = words[i].length();
            for (int j = 1; j <= n; ++j) {
                String prefix = words[i].substring(0, j);
                for (int k = 1; k <= n; ++k) {
                    String suffix = words[i].substring(n - k, n);
                    distionary.put(prefix + "$" + suffix, i);
                }
            }
        }
    }

    public int f(String pref, String suff) {
        return distionary.getOrDefault(pref + "$" + suff, -1);
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */