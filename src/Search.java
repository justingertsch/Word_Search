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
        Scanner in = new Scanner(Paths.get(args[0]));
        Search searcher = new Search();
        searcher.setPuzzle(new Puzzle(in));
        searcher.searchWords(in);
    }

    public void setPuzzle(Puzzle p)
    {
        wordPuzzle = p;
    }

    public void searchWords(Scanner s)
    {

    }

}
