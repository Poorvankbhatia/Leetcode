import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class WordAndCount {
    public String word;
    public int count;

    WordAndCount(String word,int count) {
        this.word = word;
        this.count = count;
    }
}

class WordAndCountIterator implements Iterator<WordAndCount> {

    private List<WordAndCount> wordAndCounts;
    int index;
    public WordAndCountIterator(Iterator<String> words) {

        index=0;
        wordAndCounts = new ArrayList<>();
        String next="";
        String prevWord = "";
        while (words.hasNext()) {
            int count=1;
            prevWord= prevWord.equals("")?words.next():prevWord;
            while (words.hasNext()) {
                next = words.next();
                if(next.equals(prevWord)) {
                    count++;
                    next="";
                } else {
                    break;
                }
            }
            WordAndCount wordAndCount = new WordAndCount(prevWord,count);
            wordAndCounts.add(wordAndCount);
            prevWord=next;
        }
        if(!prevWord.equals("")) {
            WordAndCount wordAndCount = new WordAndCount(prevWord,1);
            wordAndCounts.add(wordAndCount);
        }

    }

    @Override
    public WordAndCount next() {
        if(hasNext()) {
            index++;
            return wordAndCounts.get(index);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return index< wordAndCounts.size();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("foo","foo"));
        WordAndCountIterator wordAndCountIterator = new WordAndCountIterator(list.iterator());
        System.out.println(wordAndCountIterator.wordAndCounts);
    }

}