Map<Long, Integer> map = new HashMap<>();
int dfs(int idx, char[] ar, int set, boolean limit, boolean isnum) {
    long k = idx;
    k <<= 10;
    k |= set;
    k <<= 2;
    if (limit) k |= 1 << 1;
    if (isnum) k |= 1;
    if (map.containsKey(k)) return map.get(k);
    if (idx == ar.length) {
        if (set == 0) return 0;
        return 1;
    }
    int c = ar[idx] - '0';
    int ans = 0;
    if (limit) {
        int s = 0, e = 0;
        if (isnum) { // limit isnum
            for (int i = 0; i <= c; i++) {
                if ((set >> i & 1) == 1) continue;
                ans += dfs(idx + 1, ar, set | (1 << i), i == c, true);
            }
        } else { // limit notnum
            ans += dfs(idx + 1, ar, set, false, false);
            for (int i = 1; i <= c; i++) {
                if ((set >> i & 1) == 1) continue;
                ans += dfs(idx + 1, ar, set | (1 << i), i == c, true);
            }
        }
    } else {
        if (isnum) { // notlimit isnum
            for (int i = 0; i <= 9; i++) {
                if ((set >> i & 1) == 1) continue;
                ans += dfs(idx + 1, ar, set | (1 << i), false, true);
            }
        } else { // notlimit notnum
            ans += dfs(idx + 1, ar, set, false, false);
            for (int i = 1; i <= 9; i++) {
                if ((set >> i & 1) == 1) continue;
                ans += dfs(idx + 1, ar, set | (1 << i), false, true);
            }
        }
    }
    map.put(k, ans);
    return ans;
}