class Trie {
    boolean end;
    Trie next[] = new Trie[26];
    Trie() {}
    void add(String s) {
        Trie t = this;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (t.next[c - 'a'] == null) {
                t.next[c - 'a'] = new Trie();
            }
            t = t.next[c - 'a'];
            if (i == s.length() - 1) t.end = true;
        }
    }
    // 任意匹配，递归
    // 用法: trie.search(s, 0, trie);
    boolean search(String s, int idx, Trie cur) {
        if (idx == s.length()) return cur.end;
        char c = s.charAt(idx);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (cur.next[i] != null) {
                    if (search(s, idx + 1, cur.next[i])) return true;
                }
            }
        } else {
            if (cur.next[c - 'a'] == null) return false;
            return search(s, idx + 1, cur.next[c - 'a']);
        }
        return false;
    }
}