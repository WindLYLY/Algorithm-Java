public class MergeSort {
    void mergeSort(int[] ar, int l, int r, int[] tmp) { // 分治
        if (l >= r) return; // 一个元素时直接返回
        int m = l + r >> 1;
        mergeSort(ar, l, m, tmp);
        mergeSort(ar, m + 1, r, tmp);
        merge(ar, l, m, r, tmp);
    }
    void merge(int[] ar, int l, int m, int r, int[] tmp) { // 合并
		// 传递m和传递tmp是减少计算和分配内存的时间（巨大优化）
		// 使用tmp临时数组存储合并的两个数组
        int i = 0, j = l, k = m + 1;
        while (j <= m && k <= r) { // 选择较小的值插入
            tmp[i++] = ar[j] < ar[k] ? ar[j++] : ar[k++];
        }
        while (j <= m) tmp[i++] = ar[j++]; // 左边数组剩余
        while (k <= r) tmp[i++] = ar[k++]; // 右边数组剩余
        for (int n = 0; n < i; n++) {
            ar[l++] = tmp[n];
        }
    }
}