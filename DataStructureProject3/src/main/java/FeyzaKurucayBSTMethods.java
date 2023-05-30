
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 Feyza KURUCAY
1921221009
COMPUTER ENGINEERING
 */
/**
 *
 * @author Feyza Kuruçay
 */
public class FeyzaKurucayBSTMethods {
    static int filenum =1;

    FeyzaKurucayNode root;
    public FeyzaKurucayBSTMethods maketreeandRead(FeyzaKurucayBSTMethods tree,String filename, ArrayList ignorewords) throws IOException{
            FileReader f = new FileReader(filename); //I am taking html file for reading.
        BufferedReader file = new BufferedReader(f);
        String lines = "";
        while (lines != null) {
            lines = file.readLine();//I get the first line from the HTML extension file.
            if (lines != null) {
                StringTokenizer st = new StringTokenizer(lines);//I used this method to separate the words in the line from the spaces and get them word by word.
                while (st.hasMoreTokens()) {// It means while we come end of the line
                    String s = st.nextToken();//It means take the next word in order.
                    if (s.startsWith("<") || s.equals(",") || s.equals(".")) {
                        //As stated in our project file we shouldnt take SGML/HTML tags and punctions.
                        continue;
                    } else if (ignorewords.contains(s.toLowerCase())) {
                        //Also we shouldnt take the words which are in ignore words file.
                        continue;
                    } else { //I add the words I got here to my binary tree.
                        boolean isItInTree = tree.search(s);//I souldnt add the same word more than 1, I am checking it.
                        if (isItInTree == false) {
                            tree.insert(s);
                        }

                    }
                }
            }

        }
         tree.preorder(); //Method where I put the tree in the preorder order and print it to the file
         filenum++;//I am making 10 tree so I increase file number.
        return tree;
    }

    void insert(String data) {// I'm taking the whole word as a String parameter.
        if (root == null) {
            root = new FeyzaKurucayNode(data, 1);
        } else {
            FeyzaKurucayNode temp = root;
            while (temp != null) {
                if (data.compareTo(temp.data) > 0) {
                    //Here I am comparing the incoming data and the previous node. If the word comes later in alphabetical order, I add it to the right.
                    if (temp.right == null) {
                        temp.right = new FeyzaKurucayNode(data, 1);//Since I added a new word to the tree, I assign the frequency of this word as 1.
                        return;
                    }
                    temp = temp.right;
                } else if (data.compareTo(temp.data) < 0) {
                    ////Here I am comparing the incoming data and the previous node. If the word comes first in alphabetical order, I add it to the left.
                    if (temp.left == null) {
                        temp.left = new FeyzaKurucayNode(data, 1);
                        return;
                    }
                    temp = temp.left;
                } else {
                    temp.value++;
                    //If the word is a word that is already in the tree, I don't add it again, I just increase its frequency.
                    return;
                }
            }
        }

    }

    boolean search( String key){//In this method, I check whether the word I want to add to the tree is present in the tree or not.
         if (root == null) {
            return false;
        } else {
            FeyzaKurucayNode temp = root;
            while (temp != null) {
                if (key.compareTo(temp.data) > 0) {
                    temp = temp.right;//I am looking all the right side.
                } else if (key.compareTo(temp.data) < 0) {
                    temp = temp.left;////I am looking all the left side.
                } else {
                    temp.value++;//If I find the word I increase frequency and return true.
                    return true;
                }
            }
        }
         return false;
    }
    boolean isitinTree( String key){//In this method, I check whether the word I want to add to the tree is present in the tree or not.
         if (root == null) {
            return false;
        } else {
            FeyzaKurucayNode temp = root;
            while (temp != null) {
                if (key.compareTo(temp.data) > 0) {
                    temp = temp.right;//I am looking all the right side.
                } else if (key.compareTo(temp.data) < 0) {
                    temp = temp.left;////I am looking all the left side.
                } else {
                    return true;
                }
            }
        }
         return false;
    }
    int search2(String key){//Method that finds the location of the entered word in that tree and returns its frequency.
        if (root == null) {
            return 0;
        } else {
            int value=0;
            FeyzaKurucayNode temp = root;
            while (temp != null) {
                if (key.compareTo(temp.data) > 0) {
                    temp = temp.right;//I am looking all the right side.
                } else if (key.compareTo(temp.data) < 0) {
                    temp = temp.left;////I am looking all the left side.
                } else {
                    value= temp.value;
                    return value;
                }
            }
        }
         return 0;
    }

    void preorder() throws IOException {
        String name= "result"+ Integer.toString(filenum)+".txt";
//        System.out.println(name);
        File outputfile = new File(name);//I am making a new file for my output.
        if (!outputfile.exists()) {
         //I'm checking if I've created this file before. If I created it, I will update on it, if not, I will create it from scratch.
            outputfile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(outputfile, false);
        //I opened the file in false mode so that it would not overwrite, ıt will reset and write.
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
//        System.out.println("Preorder:");
        bWriter.write("Preorder : \n");
        preorder(root, outputfile, fileWriter, bWriter);
//        System.out.println();
    }

    private void preorder(FeyzaKurucayNode temp, File file, FileWriter fileWriter, BufferedWriter bwriter) throws IOException {
        if (temp != null) {
//            System.out.print(temp.data + ", " + temp.value);
            bwriter.write(temp.data + "," + temp.value + "\n");
            bwriter.flush();// I am flush the characters from the buffered writer stream.
//            System.out.println("");
            preorder(temp.left, file, fileWriter, bwriter);
            preorder(temp.right, file, fileWriter, bwriter);
        }

    }
    
}
