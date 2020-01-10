import javafx.util.Pair;

import java.util.*;

/**
 * @author yanl
 * @date 2020-01-10 8:36 上午
 */
public class OneHundredTwentySeven {
//  单向广度优先搜索
//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        // Since all words are of same length
//        int L = beginWord.length();
//        // Dictionary to hold combination of words that can be formed,
//        // from any given word, By changing one letter at a time.
//        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
//        // wordList预处理：找出所有的通用状态。记录在字典中。key：通用状态 value：具有通用状态的单词
//        wordList.forEach(
//                word -> {
//                    for (int i = 0; i < L; i++) {
//                        // key is the generic word.
//                        // value is a list of words which have the same
//                        // intermediate generic word.
//                        String newWord = word.substring(0, i) + '*' +
//                                word.substring(i + 1, L);
//                        // 没有的话就赋值一个空数组
//                        ArrayList<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<String>());
//                        transformations.add(word);
//                        allComboDict.put(newWord, transformations);
//                    }
//                }
//        );
//        System.out.println(allComboDict);
//        // 将beginWord 和 层次 1 组成的元祖放入队列中
//        Queue<Pair<String, Integer>> Q = new LinkedList<Pair<String, Integer>>();
//        Q.add(new Pair<>(beginWord, 1));
//        // 为了防止出现环，使用访问数组记录。
//        // Visited to make sure we don't repeat processing same word.
//        HashMap<String, Boolean> visited = new HashMap<>();
//        visited.put(beginWord, true);
//        /**
//         * 1.当队列中有元素的时候，取出第一个元素，记为 current_word。
//         * 2.找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
//         * 3.从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
//         * 4.对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
//         * 5.最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
//
//         */
//        while (!Q.isEmpty()) {
//            Pair<String, Integer> node = Q.remove();
//            String word = node.getKey();
//            int level = node.getValue();
//
//            for (int i = 0; i < L; i++) {
//                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
//
//                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
//                    // If at any point if we find what we are looking for i.e. the end word
//                    // we can return with the answer
//                    if (adjacentWord.equals(endWord)) {
//                        return level + 1;
//                    }
//                    // Otherwise, add it to the BFS Queue.All make it visited
//                    if (!visited.containsKey(adjacentWord)) {
//                        visited.put(adjacentWord, true);
//                        Q.add(new Pair<>(adjacentWord, level + 1));
//                    }
//                }
//            }
//        }
//        return 0;
//    }

    // 双向广度优先搜索
    private int L;
    private HashMap<String, ArrayList<String>> allComboDict;

    OneHundredTwentySeven() {
        this.L = L;
        this.allComboDict = new HashMap<String, ArrayList<String>>();
    }

    private int visitWordNode(
            Queue<Pair<String, Integer>> Q,
            HashMap<String, Integer> visited,
            HashMap<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        Integer level = node.getValue();

        for (int i = 0; i < this.L; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                if (othersVisited.containsKey(adjacentWord)) {
                    return othersVisited.get(adjacentWord) + level;
                }
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair<>(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.L = beginWord.length();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        ArrayList<String> transformations =
                                this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });

        // Queues for birdirectional BFS
        // BFS starting from beginWord
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<Pair<String, Integer>>();
        Queue<Pair<String, Integer>> Q_end = new LinkedList<Pair<String, Integer>>();

        Q_begin.add(new Pair<>(beginWord, 1));
        Q_end.add(new Pair<>(endWord, 1));

        HashMap<String, Integer> visitedBegin = new HashMap<String, Integer>();
        HashMap<String, Integer> visitedEnd = new HashMap<String, Integer>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }
            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }


        }
        return 0;
    }

    public static void main(String[] args) {
        OneHundredTwentySeven oneHundredTwentySeven = new OneHundredTwentySeven();
        int i = oneHundredTwentySeven.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(i);
    }
}

