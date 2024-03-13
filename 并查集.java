class UnionFind {
    int[] f;
    void combine(int[] f, int a, int b) {
        fa[find(a)] = find(b);
    }
    // int find(int[] f, int i) { // 递归写法，代码短，开销大，一次压缩
    //     return f[i] == i ? i : (f[i] = find(f[i]));
    // }
    int find(int[] f, int i) { // 一次循环，logn压缩
        while (f[i] != i) {
            f[i] = f[f[i]];
            i = f[i];
        }
        return i;
    }
    // int find(int[] f, int i) { // 两次循环，一次压缩
    //     int v = i, fv = f[i];
    //     while (f[i] != i) i = f[i];
    //     while (v != i) {
    //         f[v] = i;
    //         v = fv;
    //         fv = f[fv];
    //     }
    //     return i;
    // }
}