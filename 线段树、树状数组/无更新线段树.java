// 求和模板
// p一定从1开始
public class segTree {
    static void build(int[] a, int[] tree, int p, int l, int r) {
        if (l == r) {
            tree[p] = a[l];
            return;
        }
        int m = l + r >> 1;
        build(a, tree, p << 1, l, m);
        build(a, tree, p << 1 | 1, m + 1, r);
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }
    static long find(int[] tree, int p, int l, int r, int ll, int rr) {
        int mm = ll + rr >> 1;
        if (l == ll && r == rr) return tree[p];
        if (l >= mm + 1) { // 只在右边
            return find(tree, p << 1 | 1, l, r, mm + 1, rr);
        } else if (r <= mm) { // 只在左边
            return find(tree, p << 1, l, r, ll, mm);
        } else { // 在两边，ll - mm  mm+1 -- rr
            return find(tree, p << 1, l, mm, ll, mm) + find(tree, p << 1 | 1, mm + 1, r, mm + 1, rr);
        }
    }
}
