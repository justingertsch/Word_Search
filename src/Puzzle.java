/**
 * Created by Justin Gertsch on 9/8/14.
 */

import java.nio.file.Paths;
import java.util.Scanner;

public class Puzzle
{
    private char[][] wordPuzzle;
    final private int MAX_ALLOWABLE_PUZZLE_SIZE = 100;
    final private int MAX_SIZE;
    final private int MIN_SIZE = 0;
    private int rowPosition = 0;
    private int flag = 1;

    Puzzle(Scanner fileScanner)
    {
        setWordPuzzle(fileScanner);

        setSearchWords(fileScanner);
    }

    private void setWordPuzzle(Scanner s)
    {
        while(s.hasNextLine())
        {
            String line = s.nextLine().trim();

            if(line.equals("")) break;

            if(flag == 1)
            {
                MAX_SIZE = line.length();
                if(MAX_SIZE > MAX_ALLOWABLE_PUZZLE_SIZE)
                {
                    System.out.println("The puzzle entered is too large");
                    System.exit();
                }
                wordPuzzle = String[MAX_SIZE][MAX_SIZE];
                flag = 0;
            }

            setPuzzleRow(line);
            rowPosition++;

        }
    }

    private void setPuzzleRow(String fileLine)
    {
        char[] letters = fileLine.toCharArray();
        for(int i = 0; i < MAX_SIZE; i++)
            wordPuzzle[rowPosition][i] = letters[i];
    }

    private void setSearchWords(Scanner s)
    {
        while(s.hasNextLine())
        {
            String line = s.nextLine().trim();

        }
    }

    public void printPuzzle()
    {

    }


}
