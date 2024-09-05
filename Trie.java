public class Trie {

    private static final int ALPHABET_SIZE = 26;

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = this.root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }

        node.isWord = true;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            if (node.isWord) {
                node.isWord = false;
            }

            return node.isEmpty();
        }

        char c = word.charAt(index);

        if (delete(node.children[c - 'a'], word, index + 1)) {
            node.children[c - 'a'] = null;

            return node.isWord == false && node.isEmpty();
        }

        return false;
    }

    public boolean search(String word) {
        TrieNode node = this.root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }

        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = this.root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }

        return true;
    }

    private class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            this.children = new TrieNode[ALPHABET_SIZE];
            this.isWord = false;
        }

        public boolean isEmpty() {
            for (TrieNode child : this.children) {
                if (child != null) {
                    return false;
                }
            }

            return true;
        }
    }
}