//Scrabbleword class - utilizes alphagram to check for anagrams

public class ScrabbleWord {
  
    private int wordval;
    private char[] agram;
    private String actualword, alphagram;
    ScrabbleWord(String str) {
        wordval = 0;
        actualword = str.toLowerCase(); //make strings lower-case
        agram = sort(actualword.toCharArray()); //makes the alphagram of string
        for (int i = 0; i < agram.length; i++) { //makes alphagram into a String
            alphagram += agram[i];
            wordval += wordValue(agram[i]);
        }
    }
    
    public int getWordVal()  {
      return wordval;
   }
    
    public boolean isAnagram(ScrabbleWord w) {
        return this.alphagram.equals(w.alphagram); //if alphagrams are equal, they'll be anagrams
    }
    
    public String toString() { 
        return actualword + " "; //returns the actual word
    }
        
        public int wordValue (char letter) {
          if (letter == 'e' ||letter == 'a' || letter == 'o' || letter == 'i' || letter == 'n' || letter == 'r' || letter == 't')
            return 1;
          else if (letter == 'l' || letter == 's' || letter == 'u')
            return 1;
          else if (letter == 'd' || letter == 'g')
            return 2;
          else if (letter == 'b' || letter == 'c' || letter == 'm' || letter == 'p')
            return 3;
          else if (letter == 'f' || letter == 'h' || letter == 'v' || letter == 'w' || letter == 'y')
            return 4;
          else if (letter == 'k')
            return 5;
          else if (letter == 'j' || letter == 'x')
            return 8;
          else if (letter == 'q' || letter == 'z')
            return 10;
          else
            return 0;
        }
    
    public int hashCode() {
        return Math.abs(alphagram.hashCode()); //calls the String hash code method and takes the absolute value since this sometimes returns a negative value   
    }
    
    //sorts the array of characters to find the alphagram
    public char[] sort(char[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                a = exch(a, j, j-1);
            }
        }
        return a;
    }
    
    private boolean less(char v, char w) {
        return v - w <0;
    }
    
    private char[] exch(char[] a, int i, int j) {
        char swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        return a;
    }
}