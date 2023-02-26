package QuestionNo6;

public class QuestionNo6b {
    private char[] letters = {'S', 'I', 'X', 'E', 'V', 'N', 'T', 'W', 'Y'};
    private int[] values = {6, 5, 0, 8, 7, 2, 1, 3, 4};
    // method to check if the sum of the values of the words equals the value of the result
    public boolean showResult(String[] words, String result) {
        return getWordsValue(words) == Integer.parseInt(getEachValue(result));
    }

    // method to calculate the value of the words array by adding the values of each word
    int getWordsValue(String[] words) {
        int wordValue = 0;
        // loop through each word in the words array
        for (String word : words) {
            // calculate the value of each word using the getEachValue method and parse it to an integer for addition
            wordValue += Integer.parseInt(getEachValue(word));
        }
        return wordValue;
    }

    // method to calculate the value of a single word by getting the value of each letter and combining them
    String getEachValue(String word) {
        String letterValue = "";
        char letter;
        // loop through each letter in the word
        for (int i = 0; i < word.length(); i++) {
            // get the value of each letter using the getValueOf method and append it to the letterValue string
            letter = word.charAt(i);
            letterValue += getValueOf(letter);
        }
        return letterValue;
    }

    // method to get the value of a single letter by finding its index in the letters array and returning the corresponding value
    int getValueOf(char letter) {
        // search for the letter in the letters array
        for (int i = 0; i < letters.length; i++) {
            // if the letter is found, return the corresponding value from the values array
            if (letters[i] == letter) {
                return values[i];
            }
        }
        // if the letter is not found, throw an IllegalAccessError
        throw new IllegalAccessError();
    }

    // main method to test the class
    public static void main(String[] args) {
        QuestionNo6b answer = new QuestionNo6b();
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        // print the final output of whether the sum of the values of the words equals the value of the result
        System.out.println(answer.showResult(words, result));
        // print the value of the words array and the result array for confirmation/testing
        int wordsValue = answer.getWordsValue(words);
        System.out.println("Words value : " + wordsValue);
        String resultValue = answer.getEachValue(result);
        System.out.println("Result value : " + resultValue);
    }

}
