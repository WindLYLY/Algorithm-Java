import java.util.*;
import java.io.*;
public class EK_MaxCost {
    // 基于 EK 和 SPFA 的增广路算法
    // 所有费用设为相反数，找最小费用流，然后将答案转成相反数
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

        // 0源点  nn-1汇点
        // 1~m 员工
        // m+1 ~ m+n 区域

        // g为边容量 c为边费用
        long[][] g, c;
        { // 建图
            int nn = m + n + 2;
            g = new long[nn][nn];
            c = new long[nn][nn];
            for (int i = 1; i <= m; i++) {
                g[0][i] = 1; // 源点到每个员工容量为1的边
            }
            for (int i = m + 1; i <= m + n; i++) { // 区域到汇点的边容量 为区域的容量
                g[i][n - 1] = a[i - m - 1];
            }
            for (int i = 1; i <= m; i++) {
                for (int j = m + 1; j <= m + n; j++) {
                    g[i][j] = 1; // 每个员工到区域的边容量为1
                    int cc = p[i - 1][j - m - 1];
                    c[i][j] = -cc; // 喜爱度为员工到区域的费用  (取相反数 为负权重)
                    c[j][i] = cc; // 添加负权重 (相反数 为正权重)
                }
            }

            n = nn;
        }

        long minCost = 0;
        long cost;
        while ((cost = f(g, c, n)) <= 0) { // 存在增广路则计算结果
            minCost += cost;
        }

        out.println(-minCost); // 结果取相反数，即为最大费用
        out.flush();
    }
    static long f(long[][] g, long[][] c, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        vis[0] = true; // 0为起点
        q.offer(0);

        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[0] = 0;

        int[] pre = new int[n];
        Arrays.fill(pre, -1);

        while (!q.isEmpty()) { // SPFA寻找最短路
            int x = q.poll();
            vis[x] = false;
            for (int y = 0; y < n; y++) {
                if (g[x][y] > 0 && dis[x] + c[x][y] < dis[y]) { // SPFA
                    if (!vis[y]) {
                        q.offer(y);
                        vis[y] = true;
                    }
                    dis[y] = dis[x] + c[x][y];
                    pre[y] = x;
                }
            }
        }
        // n - 1为终点 s到t无通路
        // 由于最短路费用和一定是非正数，即结果是非正数，返回正数代表无通路
        if (pre[n - 1] == -1) return 1;

        int y = n - 1;
        long flow = Long.MAX_VALUE;
        while (y != 0) { // 找流量瓶颈
            int x = pre[y];
            flow = Math.min(flow, g[x][y]);
            y = x;
        }
        y = n - 1;
        while (y != 0) { // 更新最短路中正向和反向容量
            int x = pre[y];
            g[x][y] -= flow;
            g[y][x] += flow;
            y = x;
        }
        return flow * dis[n - 1];
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