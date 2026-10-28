import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellCheck {

    public static void main(String[] args) {

        // Check that a dictionary filename was provided
        if (args.length == 0) {
            System.out.println("Error: No dictionary file specified.");
            return;
        }

        // Locate the dictionary file
        File dictionaryFile = new File(args[0]);

        if (!dictionaryFile.isFile()) {
            System.out.println("Error: Dictionary file not found.");
            return;
        }

        // Create a binary search tree of strings
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        // Load every dictionary word into the tree
        try (Scanner fileScanner = new Scanner(dictionaryFile)) {

            while (fileScanner.hasNext()) {
                String word = fileScanner.next();
                tree.insert(word);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open dictionary.");
            return;
        } catch (Exception e) {
            System.out.println("Error: Unable to read dictionary.");
            return;
        }

        // Display the height after inserting all words
        System.out.println(
                "Loaded the words into a tree with height = "
                        + tree.height()
        );

        // Read words entered by the user
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String word = input.next();

            // END signals that the program should stop
            if (word.equals("END")) {
                break;
            }

            // Check whether the word exists in the tree
            if (tree.search(word) == null) {
                System.out.println(word + " is spelled wrong!");
            }
        }
    }
}
