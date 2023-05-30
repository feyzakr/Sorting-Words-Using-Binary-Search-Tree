
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 Feyza KURUCAY
1921221009
COMPUTER ENGINEERING
 */
/**
 *
 * @author Feyza Kuruçay
 */
public class FeyzaKurucayMain {

    public static void readIgnoreWords(String filename, ArrayList<String> ignorewords) throws FileNotFoundException, IOException {
        //In this method, I read the words to be ignored from the file and put them in the arraylist.
        FileReader f = new FileReader(filename);
        BufferedReader ignorefile = new BufferedReader(f);
        String line = "";
        while (line != null) {
            line = ignorefile.readLine();//I used readline because the words given to us are already line by line
            ignorewords.add(line);
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> ignorewords = new ArrayList<String>();//arraylist where I keep the ignore words
        readIgnoreWords("ignoreList.txt", ignorewords);
        ArrayList<FeyzaKurucayBSTMethods> treeList = new ArrayList<FeyzaKurucayBSTMethods>();
        FeyzaKurucayBSTMethods tree1 = new FeyzaKurucayBSTMethods();
        tree1.maketreeandRead(tree1, "cse22501.html", ignorewords);
        treeList.add(tree1);
//        System.out.println("1 "+ tree1);
        FeyzaKurucayBSTMethods tree2 = new FeyzaKurucayBSTMethods();
        tree2.maketreeandRead(tree2, "cse22502.html", ignorewords);
        treeList.add(tree2);
        FeyzaKurucayBSTMethods tree3 = new FeyzaKurucayBSTMethods();
        tree3.maketreeandRead(tree3, "cse22503.html", ignorewords);
        treeList.add(tree3);
        FeyzaKurucayBSTMethods tree4 = new FeyzaKurucayBSTMethods();
        tree4.maketreeandRead(tree4, "cse22504.html", ignorewords);
        treeList.add(tree4);
        FeyzaKurucayBSTMethods tree5 = new FeyzaKurucayBSTMethods();
        tree5.maketreeandRead(tree5, "cse22505.html", ignorewords);
        treeList.add(tree5);
        FeyzaKurucayBSTMethods tree6 = new FeyzaKurucayBSTMethods();
        tree6.maketreeandRead(tree6, "cse22506.html", ignorewords);
        treeList.add(tree6);
        FeyzaKurucayBSTMethods tree7 = new FeyzaKurucayBSTMethods();
        tree7.maketreeandRead(tree7, "cse22507.html", ignorewords);
        treeList.add(tree7);
        FeyzaKurucayBSTMethods tree8 = new FeyzaKurucayBSTMethods();
        tree8.maketreeandRead(tree8, "cse22508.html", ignorewords);
        treeList.add(tree8);
        FeyzaKurucayBSTMethods tree9 = new FeyzaKurucayBSTMethods();
        tree9.maketreeandRead(tree9, "cse22509.html", ignorewords);
        treeList.add(tree9);
        FeyzaKurucayBSTMethods tree10 = new FeyzaKurucayBSTMethods();
        tree10.maketreeandRead(tree10, "cse22510.html", ignorewords);
        treeList.add(tree10);
        FeyzaKurucayheap myheap = new FeyzaKurucayheap(10);
        String txt = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Type the word-text you want to search");
        txt = s.nextLine();//I am taking a query word from user.
        String[] wordarray = txt.split(" ");//Input could be mor4e than one word I need to search them one by one, so I split them.

        int value = 0;
        int docnum = 1;
        FeyzaKurucayBSTMethods tree = new FeyzaKurucayBSTMethods();
        for (int i = 0; i < 10; i++) {
            tree = treeList.get(i);//I am taking trees one by one from arraylist.
            for (int j = 0; j < wordarray.length; j++) {
                String word = wordarray[j];//I am taking my words one by one and I am looking for their frequency.
                if (word.startsWith("<") || word.equals(",") || word.equals(".")) {
                    //As stated in our project file we shouldnt take SGML/HTML tags and punctions.
                    continue;
                } else if (ignorewords.contains(word.toLowerCase())) {
                    //Also we shouldnt take the words which are in ignore words file.
                    continue;
                } else { //I add the words I got here to my binary tree.

                    boolean isItInTree = tree.isitinTree(word);//I check whether the word I want to add to the tree is present in the tree or not
                    //If it is in tree than I can find frequency of that word.
                    if (isItInTree == true) {
                        value += tree.search2(word);
                        //I need to find the sum of the frequencies of the entered words so i proceed by adding to the sum

                    } else {
                        value += 0;
                    }

                }

            }
            String heapname = "Doc" + docnum + " :" + value;
            //I am sending document name and frequency of input to the heap as a String
            myheap.insert(heapname);
            docnum++;
            value = 0;//I'm resetting the total because I'm starting to a new document search.
        }
        myheap.print();

    }


}
