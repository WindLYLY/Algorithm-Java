public static int kmpSearch(String s, String p) {
    int i = 0, j = 0;
    int[] next = new int[p.length()];
    buildNext(p, next);
    // i 永不回退   next[j - 1]保存当前j可以跳过的位数  j == 0时无匹配，i++
    while (i < s.length()) {
        if (s.charAt(i) == p.charAt(j)) {
            i++;
            j++;
            if (j == p.length()) {
                return i - p.length();
            }
        } else {
            if (j == 0) i++;
            else j = next[j - 1];
        }
    }
    return -1;
}
public static void buildNext(String p, int[] next) {
    // i j 为前后指针
    int i = 0, j = 1;
    while (j < next.length) {
        if (p.charAt(i) == p.charAt(j)) {
            next[j] = i + 1;
            j++;
            i++;
        } else {
            // i回退
            if (i > 0) i = next[i - 1];
            else j++;
        }
    }
}