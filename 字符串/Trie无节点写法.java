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
    boolean search(String s) {
        Trie t = this;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (t.next[c] == null) return false;
            t = t.next[c];
            if (i == s.length() - 1) return t.end;
        }
        return false;
    }
}