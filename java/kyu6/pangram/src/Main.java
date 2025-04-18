import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentenceInput = scan.nextLine().toLowerCase().trim();

        // Filter only a-z characters
        StringBuilder filteredSentence = new StringBuilder();
        for (char c : sentenceInput.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                filteredSentence.append(c);
            }
        }

        String cleanSentence = filteredSentence.toString();
        boolean isPangram = pangram(cleanSentence);
        System.out.println("The pangram is: " + isPangram);
    }

    public static boolean pangram(String sentence) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        char[] sentenceChars = sentence.toCharArray();
        quickSort(sentenceChars, 0, sentenceChars.length - 1);
        char[] removedDuplicates = removeDuplicates(sentenceChars);

        String result = new String(removedDuplicates);
        return result.equals(alphabet);
    }

    public static void quickSort(char[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(char[] arr, int begin, int end) {
        char pivot = arr[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        char temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

    public static char[] removeDuplicates(char[] array) {
        if (array.length == 0) return new char[0];

        char[] temp = new char[array.length];
        int j = 0;

        for (int i = 0; i < array.length; i++) {
            if (i == 0 || array[i] != array[i - 1]) {
                temp[j++] = array[i];
            }
        }

        return Arrays.copyOf(temp, j);
    }
}
