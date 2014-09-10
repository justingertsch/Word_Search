import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Justin Gertsch on 9/8/14.
 */
public class Search
{
    final private int MAX_ALLOWABLE_SEARCH_WORDS = 200;
    private Puzzle wordPuzzle;

    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Error: No file specified. Usage: Search.class file");
            System.exit(0);
        }

        Scanner in = new Scanner(Paths.get(args[0]));
        Search searcher = new Search();
        searcher.setPuzzle(new Puzzle(in));
        searcher.printPuzzle();
        //searcher.searchWords(in);

    }

    public void setPuzzle(Puzzle p)
    {
        this.wordPuzzle = p;
    }

    public void printPuzzle()
    {
        this.wordPuzzle.printPuzzle();
    }

    public void searchWords(Scanner s)
    {

    }

}
