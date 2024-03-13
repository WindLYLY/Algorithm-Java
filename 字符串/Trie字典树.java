class Trie {
    class TrieNode {
        boolean end;
        TrieNode[] childs = new TrieNode[26];
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (p.childs[c] == null) p.childs[c] = new TrieNode();
            p = p.childs[c];
            if (i == word.length() - 1) p.end = true;
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (p.childs[c] == null) return false;
            p = p.childs[c];
            if (i == word.length() - 1) return p.end;
        }
        return true;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if (p.childs[c] == null) return false;
            p = p.childs[c];
        }
        return true;
    }
}