List<Integer> primes = new ArrayList<>();
boolean[] isPrime = new boolean[n + 1];
Arrays.fill(isPrime, true);
for (int i = 2; i <= n; i++) {
    if (isPrime[i]) primes.add(i);
    for (int j: primes) {
        if (j * i > n) break;
        isPrime[j * i] = false;
        if (i % j == 0) break;
    }
}