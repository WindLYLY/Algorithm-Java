import java.util.*;
import java.io.*;
public class EK_MaxCost {
    // 基于EK的增广路算法
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int[] in = na();
        int n = in[0], m = in[1]; // m员工 n区域
        int[] a = na(); // a[i]表示i区域可以容纳的员工
        int[][] p = new int[m][n]; // p[i][j]表示i员工在j区域的喜爱度
        for (int i = 0; i < m; i++) {
            p[i] = na();
        }

        long[][] g = new long[1][1], c = new long[1][1];
        int[] ar = build(g, c, a, p, m, n); // {s, t, n}
        int s = ar[0], t = ar[1];
        n = ar[2];
        for (int i = 0; i < m; i++) {
            in = na();
            int x = in[0] - 1, y = in[1] - 1, w = in[2], cost = in[3];
            g[x][y] = w;
            c[x][y] = cost;
            c[y][x] = -cost; // 添加反向边
        }
        long maxFlow = 0;
        long minCost = 0;
        while (true) {
            int[] pre = new int[n]; // 保存路径
            Arrays.fill(pre, -1);

            long[] dis = new long[n]; // s到i的费用
            Arrays.fill(dis, Long.MIN_VALUE);
            dis[s] = 0;

            if (!find(g, c, n, s, t, pre, dis)) break; // 不存在s到t的最短路，结束算法

            long flow = Long.MAX_VALUE; // 当前最短路的流量瓶颈
            int tmp = t;
            while (tmp != s) { // 遍历路径寻找流量瓶颈
                int next = pre[tmp];
                flow = Math.min(flow, g[next][tmp]);
                tmp = next;
            }
            tmp = t;
            while (tmp != s) { // 用流量瓶颈更新容量
                int next = pre[tmp];
                g[next][tmp] -= flow;
                g[tmp][next] += flow;
                tmp = next;
            }
            minCost += flow * dis[t]; // 计算最小费用
            maxFlow += flow; // 计算最大流
        }
        out.println(maxFlow + " " + minCost);
        out.flush();
    }
    static int[] build(long[][] g, long[][] c, int[] a, int[][] p, int m, int n) {
        int s = 0, t = m + n + 1;
        int nn = m + n + 2;
        g = new long[nn][nn];
        c = new long[nn][nn];
        for (int i = 0; i < m; i++) {
            g[s][i + 1] = 1; // 起点到每个员工有一条为1的边
        }
        // 员工 1 ~ m  区域 m+1 ~ m+n
        for (int i = 0; i < n; i++) { // 区域到t的边为区域的容量
            g[i + m + 1][t] = a[i];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[i + 1][j + m + 1] = 1; // 每个员工到区域的边为1
                c[i + 1][j + m + 1] = p[i][j]; // 喜爱度为员工到区域的费用，其他边费用为0
            }
        }
        return new int[]{s, t, nn};
    }
    static boolean find(long[][] g, long[][] c, int n, int s, int t, int[] pre, long[] dis) { // SPFA可以处理负权
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        vis[s] = true;
        q.offer(s);

        while (!q.isEmpty()) {
            int x = q.poll();
            vis[x] = false;
            for (int y = 0; y < n; y++) {
                if (g[x][y] == 0 || vis[y]) continue; // 无容量 || 在队列中跳过
                if (dis[x] + c[x][y] <= dis[y]) continue; // 找最大费用，费用较小跳过
                vis[y] = true;
                q.offer(y);
                dis[y] = dis[x] + c[x][y];
                pre[y] = x;
            }
        }

        return vis[t]; // 返回s到t是否为通路
    }
    static int ni() throws IOException {
        return Integer.parseInt(sc.readLine());
    }
    static int[] na() throws IOException {
        String[] in = sc.readLine().split(" ");
        int[] a = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            a[i] = Integer.parseInt(in[i]);
        }
        return a;
    }
}