char[] low, high;
Map<Long, Long> map = new HashMap<>();
long dfs(int idx, boolean lowlimit, boolean highlimit) {
    long k = idx;
    k <<= 2;
    if (lowlimit) k |= 1 << 1;
    if (highlimit) k |= 1;
    if (map.containsKey(k)) return map.get(k);
    // check
    // if (idx >= high.length - len) {
    //     if (!highlimit && !lowlimit) return 1;
    //     if (highlimit) {
    //         String s = String.valueOf(high);
    //         if (Long.parseLong(s.substring(idx, high.length)) < suffix) return 0;
    //     }
    //     if (lowlimit) {
    //         String s = String.valueOf(low);
    //         if (Long.parseLong(s.substring(idx, low.length)) > suffix) return 0;
    //     }
    //     return 1;
    // }
    int lo = lowlimit ? low[idx] - '0' : 0, hi = highlimit ? high[idx] - '0' : 9;
    long ans = 0;
    for (int i = lo; i <= Math.min(hi, limit); i++) {
        ans += dfs(idx + 1, lowlimit && i == lo, highlimit && i == hi);
    }
    map.put(k, ans);
    return ans;
}