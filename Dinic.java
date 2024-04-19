import java.util.*;
import java.io.*;
public class Dinic {
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static long ans = 0;
    public static void main(String[] args) throws IOException {
        // Dinic's Algorithm
        int[] in = na();
        int n = in[0], m = in[1], s = in[2] - 1, t = in[3] - 1;
        long[][] g = new long[n][n]; // 初始化邻接矩阵
        for (int i = 0; i < m; i++) {
            in = na();
            int x = in[0] - 1, y = in[1] - 1, v = in[2];
            g[x][y] += v;
        }
        while (divide(g, new long[n][n], s, t, n)) { // 寻找分层图直到s到t无通路

        }
        out.println(ans);
        out.flush();
    }
    static boolean divide(long[][] g, long[][] a, int s, int t, int n) { // 寻找分层图，存在a中
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        boolean[] vis = new boolean[n];
        vis[s] = true;
        while (!q.isEmpty()) { // 使用队列分层
            int m = q.size();
            while (m-- > 0) {
                int r = q.poll();
                for (int i = 0; i < n; i++) {
                    if (g[r][i] > 0 && !vis[i]) {
                        q.offer(i);
                        a[r][i] = g[r][i]; // 保存分层图
                        vis[i] = true;
                    }
                }
            }
        }
        if (!vis[t]) return false;
        long flow = 0;
        while ((flow = find(a, g, s, t, n, Long.MAX_VALUE)) > 0) { // 在分层图中dfs，直到s到t无通路
            ans += flow;
        }
        return true;
    }
    static long find(long[][] a, long[][] g, int s, int t, int n, long min) { // dfs在a中寻找阻塞流，并修改g
        if (s == t) { // dfs找到终点 结束递归条件
            return min;
        }
        for (int i = 0; i < n; i++) {
            if (a[s][i] > 0) {
                long tmp = a[s][i];
                a[s][i] = 0; // 保证每个边只用一次
                long v = find(a, g, i, t, n, Math.min(min, tmp)); // i作起点递归
                if (v > 0) { // 找到一条路径
                    a[s][i] = tmp - v;
                    g[s][i] -= v; // 同时修改g
                    g[i][s] += v; // 添加反向边
                    return v;
                }
                a[s][i] = tmp; // 回溯恢复边
            }
        }
        return -1;
    }
    static int ni() throws IOException {
        return Integer.parseInt(sc.readLine());
    }
    static int[] na() throws IOException {
        String[] in = sc.readLine().split(" ");
        int n = in.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Integer.parseInt(in[i]);
        }
        return ans;
    }
}