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
            found_word:
            for(int i = 0; i < puzzleSize; i++)
            {
                for(int j = 0; j < puzzleSize; j++)
                {

                    // found a match to a letter
                    if(letters[letterPosition] == wordPuzzle.getLetter(i,j))
                    {
                        if(directionalSearch(i, j, letters, letterPosition))
                        {
                            break found_word;
                        }
                    }
                }
            }
        }
    }

    private boolean directionalSearch(int row, int column, char[] letters, int letterPos)
    {
        String[] directionList = {"r","rd","d","ld","l","lu","u","ru"};
        for(String dir: directionList)
            if (continueSearch(row, column, dir, letterPos, letters))
                return true;

    }

    // This is implemented recursively to continue searching if letters continue matching.
    // If the last letter matches it will return true, thus indicating a found word.
    private boolean continueSearch(int row, int column, String direction, int letterPos, char[] letters)
    {
        letterPos++;
        // this won't work for word remain changes. Fix it
        if(wordTooLarge(row, column, direction, letters.length))
            return false;

        if(letters[letterPos] == wordPuzzle.getLetter(row,column))
        {
            if((letters.length - 1) == letterPos)
                return true;
            else
                continueSearch(row, column, direction, letterPos, letters);
        }

        return false;

    }

    private boolean wordTooLarge(int row, int column, String direction, int wordLength)
    {
        switch (direction)
        {
            case "r":
                return ((column + (wordLength - 1)) > (puzzleSize - 1));

            case "rd":
                return ((column + (wordLength - 1)) > (puzzleSize - 1)) || ((row + (wordLength - 1)) > (puzzleSize - 1));

            case "d":
                return ((row + (wordLength - 1)) > (puzzleSize - 1));

            case "ld":
                return ((column - (wordLength - 1)) < ZERO) || ((row + (wordLength - 1)) > (puzzleSize - 1));

            case "l":
                return ((column - (wordLength - 1)) < ZERO);

            case "lu":
                return ((column - (wordLength - 1)) < ZERO) || ((row - (wordLength - 1)) < ZERO);

            case "u":
                return ((row - (wordLength - 1)) < ZERO);

            case "ru":
                return ((column + (wordLength - 1)) > (puzzleSize - 1)) || ((row - (wordLength - 1)) < ZERO);

            default:
                System.out.println("Invalid search direction specified. Search terminating...");
                System.exit(0);
                return false;
        }
    }

}
