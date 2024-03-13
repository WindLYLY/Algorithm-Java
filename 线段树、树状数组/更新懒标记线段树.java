class segTree {
    // 注意调用参数
    static void build(int[] a, long[] tree, int p, int l, int r) { // 数组初始化
        if (l == r) {
            tree[p] = a[l];
            return;
        }
        int m = l + r >> 1;
        build(a, tree, p << 1, l, m);
        build(a, tree, p << 1 | 1, m + 1, r);
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }
    static void update(long[] tree, long[] mark, int p, int k, int l, int r, int ll, int rr) { // 区间更新
        int mm = ll + rr >> 1;
        if (l == ll && r == rr) { // 懒标记
            tree[p] += k * (r - l + 1);
            if (r > l) mark[p] += k;
            return;
        }
        push_mark(tree, mark, p, ll, rr);
        if (l >= mm + 1) { // 只在右边
            update(tree, mark, p << 1 | 1, k, l, r, mm + 1, rr);
        } else if (r <= mm) { // 只在左边
            update(tree, mark, p << 1, k, l, r, ll, mm);
        } else { // 在两边，ll - mm  mm+1 -- rr
            update(tree, mark, p << 1, k, l, mm, ll, mm);
            update(tree, mark, p << 1 | 1, k, mm + 1, r, mm + 1, rr);
        }
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }
    static long find(long[] tree, long[] mark, int p, int l, int r, int ll, int rr) { // 区间查询
        int mm = ll + rr >> 1;
        if (l == ll && r == rr) return tree[p];
        push_mark(tree, mark, p, ll, rr);
        if (l >= mm + 1) { // 只在右边
            return find(tree, mark, p << 1 | 1, l, r, mm + 1, rr);
        } else if (r <= mm) { // 只在左边
            return find(tree, mark, p << 1, l, r, ll, mm);
        } else { // 在两边，ll - mm  mm+1 -- rr
            return find(tree, mark, p << 1, l, mm, ll, mm) + find(tree, mark, p << 1 | 1, mm + 1, r, mm + 1, rr);
        }
    }
    static void push_mark(long[] tree, long[] mark, int p, int l, int r) { // 推懒标记
        if (mark[p] == 0) return;
        int m = l + r >> 1;
        mark[p << 1] += mark[p];
        tree[p << 1] += mark[p] * (m - l + 1);
        mark[p << 1 | 1] += mark[p];
        tree[p << 1 | 1] += mark[p] * (r - m);
        mark[p] = 0;
    }
}