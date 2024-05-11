//import java.util.HashMap;
//import java.util.Map;
//
//class TrieNode {
//    Map<Character, TrieNode> children;
//    boolean isEndOfWord;
//
//    public TrieNode() {
//        children = new HashMap<>();
//        isEndOfWord = false;
//    }
//}
//
//public class Dictionary {
//    private TrieNode root;
//
//    public Dictionary() {
//        root = new TrieNode();
//    }
//
//    public void insert(String word, String translation) {
//        TrieNode current = root;
//        for (char c : word.toCharArray()) {
//            current.children.putIfAbsent(c, new TrieNode());
//            current = current.children.get(c);
//        }
//        current.isEndOfWord = true;
//        // Store translation with the last node of the word
//        current.children.put('$', new TrieNode());
//        current.children.get('$').children.put('=', new TrieNode());
//        current.children.get('$').children.get('=').children.put('=', new TrieNode());
//        current.children.get('$').children.get('=').children.put('>', new TrieNode());
//        current.children.get('$').children.get('=').children.get('>').children.put('=', new TrieNode());
//        current.children.get('$').children.get('=').children.get('>').children.get('=').children.put('$', new TrieNode());
//        TrieNode translationNode = current.children.get('$').children.get('=').children.get('>').children.get('=').children.get('$');
//        for (char c : translation.toCharArray()) {
//            translationNode.children.putIfAbsent(c, new TrieNode());
//            translationNode = translationNode.children.get(c);
//        }
//        translationNode.isEndOfWord = true;
//    }
//
//    public String search(String word) {
//        TrieNode current = root;
//        for (char c : word.toCharArray()) {
//            if (!current.children.containsKey(c)) {
//                return null; // Word not found
//            }
//            current = current.children.get(c);
//        }
//        if (current.isEndOfWord) {
//            // Retrieve translation
//            StringBuilder translation = new StringBuilder();
//            current = current.children.get('$').children.get('=').children.get('>').children.get('=').children.get('$');
//            while (!current.isEndOfWord) {
//                for (char c : current.children.keySet()) {
//                    translation.append(c);
//                    current = current.children.get(c);
//                    break;
//                }
//            }
//            return translation.toString();
//        } else {
//            return null; // Word not found
//        }
//    }
//
//    public static void main(String[] args) {
//        Dictionary dictionary = new Dictionary();
//        // Insert words and their translations
//        dictionary.insert("hello", "olá");
//        dictionary.insert("world", "mundo");
//        dictionary.insert("apple", "maçã");
//
//        // Search for translations
//        System.out.println("Translation of 'hello': " + dictionary.search("hello"));
//        System.out.println("Translation of 'world': " + dictionary.search("world"));
//        System.out.println("Translation of 'apple': " + dictionary.search("apple"));
//        System.out.println("Translation of 'banana': " + dictionary.search("banana")); // Should return null
//    }
//}

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
