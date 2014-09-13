import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Justin Gertsch on 9/8/14.
 */
public class Search
{
    final private int MAX_ALLOWABLE_SEARCH_WORDS = 200;
    private Puzzle wordPuzzle = null;
    private int puzzleSize;
    final private int ZERO = 0;
    private int wordCount = 0;


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
        checkPuzzleSet();
        this.wordPuzzle.printPuzzle();
    }

    public void searchWords(Scanner s)
    {
        checkPuzzleSet();
        while(s.hasNextLine())
        {
            wordCount++;
            if(wordCount <= MAX_ALLOWABLE_SEARCH_WORDS)
            {
                String word = s.nextLine().trim();
                char[] letters = word.toCharArray();
                int letterPosition = 0;
                boolean foundWord = false;

                // go through puzzle
                found_word:
                for (int i = 0; i < puzzleSize; i++)
                {
                    for (int j = 0; j < puzzleSize; j++)
                    {

                        // found a match to a letter
                        if (letters[letterPosition] == wordPuzzle.getLetter(i, j))
                        {
                            String res = directionalSearch(i, j, letters, letterPosition);

                            if (!res.equals("notfound"))
                            {
                                foundWord = true;
                                int endposrow = i;
                                int endposcol = j;
                                for (int k = 1; k <= letters.length - 1; k++)
                                {
                                    endposcol = getDirectionColumn(endposcol, res);
                                    endposrow = getDirectionRow(endposrow, res);
                                }
                                System.out.println(word + " found at start: " + i + ", " + j + " end: " + endposrow + ", " + endposcol);
                                break found_word;
                            }
                        }
                    }
                }
                if(!foundWord)
                    System.out.println(word +" not found");
            }
            else
            {
                System.out.println("You have reached the limit of searchable words. Program terminating...");
                System.exit(0);
            }
        }
    }

    private String directionalSearch(int row, int column, char[] letters, int letterPos)
    {
        String[] directionList = {"r","rd","d","ld","l","lu","u","ru"};
        for(String dir: directionList)
            if(!wordTooLarge(row, column, dir, letters.length))
                if (continueSearch(row, column, dir, letterPos, letters))
                    return dir;

        return "notfound";

    }

    // This is implemented recursively to continue searching if letters continue matching.
    // If the last letter matches it will return true, thus indicating a found word.
    private boolean continueSearch(int row, int column, String direction, int letterPos, char[] letters)
    {
        letterPos++;

        // get new coords int that direction of search
        row = getDirectionRow(row,direction);
        column = getDirectionColumn(column,direction);

        if(letters[letterPos] == wordPuzzle.getLetter(row,column))
        {
            if((letters.length - 1) == letterPos)
                return true;
            else
                if(continueSearch(row, column, direction, letterPos, letters))
                    return true;
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

    // changes to number to the correction position according to the direction specified
    private int getDirectionColumn(int col, String dir)
    {
        if(dir.equals("r") || dir.equals("rd") || dir.equals("ru"))
        {
            return col + 1;
        }
        else if(dir.equals("u") || dir.equals("d") )
        {
            return col;
        }
        else if(dir.equals("l") || dir.equals("ld") || dir.equals("lu"))
        {
            return col - 1;
        }
        else
        {
            System.out.println("Invalid search direction specified. Search terminating...");
            System.exit(0);
            return 0;
        }
    }

    // changes to number to the correction position according to the direction specified
    private int getDirectionRow(int row, String dir)
    {
        if(dir.equals("d") || dir.equals("rd") || dir.equals("ld"))
        {
            return row + 1;
        }
        else if(dir.equals("l") || dir.equals("r") )
        {
            return row;
        }
        else if(dir.equals("u") || dir.equals("lu") || dir.equals("ru"))
        {
            return row - 1;
        }
        else
        {
            System.out.println("Invalid search direction specified. Search terminating...");
            System.exit(0);
            return 0;
        }
    }

    private void checkPuzzleSet()
    {
        if (this.wordPuzzle == null)
        {
            System.out.println("Word puzzle not set. Program terminating...");
            System.exit(0);
        }
    }



}
