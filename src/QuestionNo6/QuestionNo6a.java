package QuestionNo6;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class QuestionNo6a {
    //huffman node is a node in the huffman tree created during encoding
    class HuffmanNode{
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;
    }
    //prints the encoded huffman code
    //prints the encoded huffman code for each character in the tree
    public static void printCode(HuffmanNode root, String s){
        //if the root node is a leaf node, i.e., it doesn't have any children,
        //then it is a character node, so print the character and its code
        if(root.left==null && root.right==null && Character.isLetter(root.c)){
            System.out.println(root.c+":"+s);
            return;
        }
        //if the node is not a leaf node, recursively traverse the left and right children of the node
        printCode(root.left,s+"0");
        printCode(root.right,s+"1");
    }

    //encoding
    public HuffmanNode encode(char[] charArray, int[] charFreq){
        int n=charArray.length;
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());

        //create Huffman nodes for each character in the input and add it to the priority queue
        for(int i=0; i<n; i++){
            HuffmanNode hn = new HuffmanNode();
            hn.c=charArray[i];
            hn.data=charFreq[i];
            hn.left=null;
            hn.right=null;
            q.add(hn);
        }

        HuffmanNode root=null;
        //iterate until there is only one node left in the priority queue
        while(q.size()>1){
            //get the two nodes with lowest frequencies
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data+y.data;
            //the parent node doesn't contain a character
            f.c='-';
            f.left=x;
            f.right=y;
            root=f;
            q.add(f);
        }
        //print the code for each character in the huffman tree
        printCode(root,"");

        return root;
    }

    //decoding the huffman tree
    public void decode(HuffmanNode root, String str){
/*Input:
 size: is the number of character in the huffman tree
root: is the parent node
str: is the 0 and 1 value that is created by encode function
*/

    /*Output:
    function prints the character and its frequency*/

        ArrayList characters= new ArrayList<>();
        ArrayList frequency=new ArrayList<>();
        int i=0;
        //iterate over the encoded string and traverse the tree to find each character in the tree
        while(i<str.length()){
            HuffmanNode current = root;
            while (current.c=='-'){
                if(str.charAt(i)=='0'){
                    current=current.left;
                    i++;
                }
                else {
                    current=current.right;
                    i++;
                }
            }
            char c = current.c;
            int f =current.data;
            characters.add(current.c);
            frequency.add(current.data);
        }
        //print the decoded characters and their frequencies
        printDecode(characters,frequency);
    }

    //helper function to print the decoded characters and their frequencies
    public static void printDecode(ArrayList characters, ArrayList frequencies){
        for (int i=0; i<characters.size(); i++){
            System.out.println(characters.get(i)+":"+frequencies.get(i));
        }
    }

    //driver method
    public static void main(String[] args) {
        char[] ch={'A','B','C','D','E'};
        int[] fre= {4,7,3,2,7};
        QuestionNo6a h = new QuestionNo6a();
        HuffmanNode hn= h.encode(ch,fre);
        System.out.println(hn.data);
        String cha = "000100111011";
        h.decode(hn,cha);
    }
}
