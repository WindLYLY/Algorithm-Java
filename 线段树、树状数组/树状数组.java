// tree 的下标为1 ~ n
// update中 v 的值为增量，而不是结果值
static void update(int[] tree, int i, int v) {
    while (i < tree.length) {
        tree[i] += v;
        i += i & -i;
    }
}
static int search(int[] tree, int i) {
    int ans = 0;
    while (i > 0) {
        ans += tree[i];
        i -= i & -i;
        // i &= i - 1;
    }
    return ans;
}