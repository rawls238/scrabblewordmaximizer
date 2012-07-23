//class to construct a hash table of all words in the Scrabble Dictionary and return list of highest scores with the set of words
 
 import java.util.*;
 import java.io.*;
 
 public class ScrabbleMaximizer {
     public static final int size = 100000; 
     public static ScrabbleWord[] top10 = new ScrabbleWord[10];
     public static int valof10 = 0; //to try to improve efficiency let's only look for valid words IF the value of the letters would be in top 10
     public static LinkedList<ScrabbleWord> [] table = (LinkedList<ScrabbleWord>[]) new LinkedList[size];
     
     public static void main(String[] args) {
         Scanner fileScanner;
         Scanner console = new Scanner (System.in);
         try {
             fileScanner = new Scanner (new File ("TWL06.TXT")); //reads in File
         } catch (IOException e) {
             System.out.println("Unable to open file. "+e.getMessage());
             return;
         } 
        
         while (fileScanner.hasNext()) {
             ScrabbleWord word = new ScrabbleWord(fileScanner.nextLine());
             int hash = word.hashCode()%size; //gets hashCode for particular word
             if(table[hash] == null) 
                 table[hash] = new LinkedList<ScrabbleWord>(); //initializes linked list
             table[hash].add(word);    
         }
        
         while(console.hasNext()) {
             String str = console.next();
             maximize(str);
             for (int j = 0; j < top10.length; j++) {
               if(top10[j] != null)
              System.out.println(top10[j].toString() + " " + top10[j].getWordVal());
               top10[j] = null;
               valof10 = 0;
             }
             
         } 
     }
     
     public static void maximize (String str) {
       int length = str.length();
       ifTop(new ScrabbleWord(str));
       for (int i = str.length() ; i > 1; i--) {
         allcombos(str, i);
       }
     }
     
     
     //finds out if combination of letters is a word and if it is in the top 10
     private static void ifTop (ScrabbleWord w) {
       int hash = w.hashCode()%size;
              if (w.getWordVal() > valof10) {
                 if (table[hash] != null) { //makes sure that there is something at the particular hash function in the array
                     for (ScrabbleWord anagram : table[hash]) {//iterates through the linked list at the hash value of the word in the array
                         if (w.isAnagram(anagram)) { //all anagrams will have the same hash value but not all words with the same hash value will be anagrams
                             int index = findTopIndex(anagram);
                             if (index == -1)
                               continue;
                             for (int i = top10.length-1; i > index; i--) {
                              top10[i] = top10[i-1];
                             }
                             top10[index] = anagram;
                             if(top10[9] != null)
                             valof10 = top10[9].getWordVal();
                           //System.out.println(w.toString());
                         } 
                     }
                 }
             }
     
     }
     
     private static void allcombos(String str, int num) {
       if (num == 2) 
         return;
       for (int i = 0; i < num; i++) {
         String curstring;
         if (i == 0)
           curstring = str.substring(1);
         else 
           curstring = str.substring(0, i) + str.substring (i+1);
         ifTop(new ScrabbleWord(curstring));
         allcombos(curstring, num-1);
       }
     }
         
         
     public static int findTopIndex(ScrabbleWord word) {
      int wordval = word.getWordVal();
      for (int i = 0; i < top10.length; i++) {
        if (top10[i] == null)
          return i;
        if(word.toString().equals(top10[i].toString())) {
          return -1; //if we have a duplicate, make sure it doesn't get in again
        }
       if (wordval >= top10[i].getWordVal()) {
        return i;
       }
      }
      return 0; //to make the the compiler happy -> this should NEVER be reached
     }
 }
     
 
