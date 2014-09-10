import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Justin Gertsch on 9/8/14.
 */
public class Search
{
    final private int MAX_ALLOWABLE_SEARCH_WORDS = 200;
    private Puzzle wordPuzzle;
    private int puzzleSize;
    final private int ZERO = 0;




    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Error: No file specified. Usage: Search.class file");
            System.exit(0);
        }

        Scanner in = null;

        try
        {
            in = new Scanner(new File(args[0]));

        }
        catch (IOException e)
        {
            System.out.println("Problem opening file.");
            e.printStackTrace();
            System.exit(0);
        }

        Search searcher = new Search();
        searcher.setPuzzle(new Puzzle(in));
        searcher.printPuzzle();
        searcher.searchWords(in);

    }

    public void setPuzzle(Puzzle p)
    {
        this.wordPuzzle = p;
        this.puzzleSize = p.getPuzzleSize();
    }

    public void printPuzzle()
    {
        this.wordPuzzle.printPuzzle();
    }

    public void searchWords(Scanner s)
    {
        while(s.hasNextLine())
        {
            String word = s.nextLine().trim();
            char[] letters = word.toCharArray();
            int letterPosition = 0;
            int startWordRowPosition = 0;
            int startWordColumnPosition = 0;

            // go through puzzle
            for(int i = 0; i < puzzleSize; i++)
            {
                for(int j = 0; j < puzzleSize; j++)
                {

                    // found a match to a letter
                    if(letters[letterPosition] == wordPuzzle.getLetter(i,j))
                    {
                        directionalSearch(letters);
                    }
                }
            }
        }
    }

    private void directionalSearch(char[] letters)
    {
        // search right
        // search right down
        // search down
        // search left down
        // search left
        // search left up
        // search up
        // search right up
    }

    private boolean continueSearch(int row, int column, String direction, int letterPos, char[] letters)
    {
        letterPos++;
        //implement this recursively
        // return whether it was found or not.   
    }

}
