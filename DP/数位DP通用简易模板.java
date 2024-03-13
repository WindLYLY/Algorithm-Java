Map<Long, Long> map = new HashMap<>();
long dfs(int idx, char[] ar, boolean limit) {
    long k = idx;
    k <<= 1;
    if (limit) k |= 1;
    if (map.containsKey(k)) return map.get(k);
    if (idx == ar.length) return 1;
    int c = ar[idx] - '0';
    int lo = 0, hi = limit ? c : 9;
    long ans = 0;
    for (int i = lo; i <= hi; i++) {
        ans += dfs(idx + 1, ar, limit && i == c);
    }
    map.put(k, ans);
    return ans;
}