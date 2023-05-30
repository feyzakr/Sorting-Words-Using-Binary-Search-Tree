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
public class FeyzaKurucayheap {

    String heap[];
    private int size;

    public FeyzaKurucayheap(int N) {
        heap = new String[N];
        size = 0;
    }

    int parent(int index) {
        return (index - 1) / 2;
    }

    void swap(int i, int j) {
        String temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    void insert(String fulltxt) {
        //I am sending the document name and the text of the searched word in that document as STRİNG key to the elements of the heap here.
        if(size == 0){
            //If it's the first time an element comes to the Heap, I add them directly.
            heap[size] = fulltxt;
            size++;
        }
        else if(size < heap.length) {
            heap[size] = fulltxt; //Firstly I'm adding it to the end
            int current = size;
            int previous = size-1;
           // Here I need to move forward with the latest (previous) addition by checking the new one, so I am comparing with the last addition, not the parent.
            String text1 = heap[current];
            String[] wordarray1 = text1.split(":");
            //I am sending String to the elements of the Heap, but when adding to the Heap, those elements must be sorted by the number which string contains.
            //and this number is located after the colon,thats why I am split it from colon.
            String w1 = wordarray1[1];
            String text = heap[previous];
            String[] wordarray = text.split(":");
            String w = wordarray[1];
            
            while (Integer.parseInt(w1) > Integer.parseInt(w)) {
                //Those numbers could be bigger than 9, so ASCII may cause some errors, thats why I compare them as an Integer.
                swap(current, previous);
                if(previous-1<0){
                    //When it came to root, it could drop to negative numbers and give an IndexOutOfBound, that's why I did this check.
                    break;
                }
                //Since the texts will change with each comparison after the first comparison, I made the above transformation here as well.
                current = previous;
                previous = previous-1;
                text1 = heap[current];
                wordarray1 = text1.split(":");
                w1 = wordarray1[1];
                text = heap[previous];
                wordarray = text.split(":");
                w = wordarray[1];
            }
            size++;
        } else {
            System.out.println("Heap is full!");
        }
    }

    void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i] + " ");
        }
        System.out.println();
    }
}
