
class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[256]; // Increase size to accommodate wider range of characters
        isEndOfWord = false;
    }
}

public class Dictionary {
    private TrieNode root;

    public Dictionary() {
        root = new TrieNode();
    }

    public void insert(String word, String translation) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c;
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;

        // Store translation
        TrieNode translationNode = current;
        for (char c : translation.toCharArray()) {
            int index = c;
            if (translationNode.children[index] == null) {
                translationNode.children[index] = new TrieNode();
            }
            translationNode = translationNode.children[index];
        }
        translationNode.isEndOfWord = true;
    }

    public String search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c;
            if (current.children[index] == null) {
                return null; // Word not found
            }
            current = current.children[index];
        }
        if (current.isEndOfWord) {
            // Retrieve translation
            StringBuilder translation = new StringBuilder();
            while (!current.isEndOfWord) {
                for (int i = 0; i < current.children.length; i++) {
                    if (current.children[i] != null) {
                        translation.append((char) i);
                        current = current.children[i];
                        break;
                    }
                }
            }
            return translation.toString();
        } else {
            return null; // Word not found
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        // Insert words and their translations
        dictionary.insert("hello", "olá");
        dictionary.insert("world", "mundo");
        dictionary.insert("apple", "maçã");

        // Search for translations
        System.out.println("Translation of 'hello': " + dictionary.search("hello"));
        System.out.println("Translation of 'world': " + dictionary.search("world"));
        System.out.println("Translation of 'apple': " + dictionary.search("apple"));
        System.out.println("Translation of 'banana': " + dictionary.search("banana")); // Should return null
    }
}
