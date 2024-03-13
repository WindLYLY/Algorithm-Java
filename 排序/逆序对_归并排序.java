// 使用mergeSort方法前要初始化tmp数组，长度为ar.length
// 第一次调用mergeSort传递第一个下标0和最后一个下标ar.length - 1
class MergeSort {
	long ans = 0;
	int[] tmp;
	void mergeSort(int[] ar, int l, int r) { // 分治
		if (l >= r) return; // 一个元素时直接返回
		int m = l + r >> 1;
		mergeSort(ar, l, m);
		mergeSort(ar, m + 1, r);
		merge(ar, l, m, r);
	}
	void merge(int[] ar, int l, int m, int r) { // 合并
		// 使用临时数组存储合并的两个数组
		int i = 0, j = l, k = m + 1;
		int cntl = m - l + 1;
		while (j <= m && k <= r) { // 选择较小的值插入
			if (ar[k] < ar[j]) {
				ans += cntl;
				tmp[i++] = ar[k++];
			} else {
				cntl--;
				tmp[i++] = ar[j++];
			}
		}
		while (j <= m) tmp[i++] = ar[j++]; // 左边数组剩余
		while (k <= r) tmp[i++] = ar[k++]; // 右边数组剩余
		for (int n = 0; n < i; n++) {
			ar[l++] = tmp[n];
		}
	}
}