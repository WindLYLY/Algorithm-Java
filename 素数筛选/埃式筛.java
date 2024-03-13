int max = (int) 1e5;
boolean[] isprime = new boolean[max + 1];
Arrays.fill(isprime, true);
for (int i = 2; i <= max; i++) {
    if (!isprime[i]) continue;
    for (int j = i; j * i <= max; j++) {
        isprime[j * i] = false;
    }
}