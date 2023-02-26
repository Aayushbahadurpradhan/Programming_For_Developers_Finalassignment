package QuestionNo6;

//arko class ma banaunai huff ma priorityqueue sort garna use garnai comparator
import java.util.Comparator;

class MyComparator implements Comparator<QuestionNo6a.HuffmanNode> {
    public int compare(QuestionNo6a.HuffmanNode x, QuestionNo6a.HuffmanNode y) {
        return x.data - y.data;
    }
}