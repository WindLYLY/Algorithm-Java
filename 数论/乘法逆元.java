// 拓展欧几里得除法
// 调用时输入(a, 1, m, 0)，求a关于m的逆元
// 结果输出(ans + mod) % mod
static int mod = (int) 1e9 + 7;
static int cal(int a, int ra, int b, int rb) {
    if (b == 0) return 0; // 无乘法逆元
    if (b == 1) return rb; // 找到逆元
    int rc = ra - (a / b) * rb;
    return cal(b, rb, a % b, rc) % mod;
}