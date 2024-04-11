class Manacher {
    public String get(String t) {
        // manacher
        String s = build(t);
        int n = s.length();
        int l = 0, r = 0;
        int[] a = new int[n];
        int max = 0, idx = 0;
        // start = i - a[i] >> 1
        // 注意别遍历0和n-1
        for (int i = 1; i < n - 1; i++) {
            int j = l + r - i;
            // 防止i和i的右边界超出r
            if (i < r) a[i] = Math.min(a[j], r - i);
            while (s.charAt(i + 1 + a[i]) == s.charAt(i - 1 - a[i])) a[i]++;
            if (i + a[i] > r) {
                r = i + a[i];
                l = i - a[i];
            }
            // 保存最长回文子串长度 和 起始索引
            if (a[i] > max) {
                max = a[i];
                // 起始索引计算方法
                idx = i - a[i] >> 1;
            }
        }
        return t.substring(idx, idx + max);
    }
    // 预处理字符串，方便计算
    String build(String t) {
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (char c: t.toCharArray()) {
            sb.append('#');
            sb.append(c);
        }
        sb.append('#');
        sb.append('$');
        return sb.toString();
    }
}