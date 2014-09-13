import java.util.Scanner;

/**
 * Created by Justin Gertsch on 9/8/14.
 */


public class Puzzle
{
    private char[][] wordPuzzle;
    final private int MAX_ALLOWABLE_PUZZLE_SIZE = 100;
    private int MAX_SIZE;
    private int rowPosition = 0;
    private int flag = 1;

    Puzzle(Scanner fileScanner)
    {
        while(fileScanner.hasNextLine())
        {
            String line = fileScanner.nextLine().trim();

            if(line.equals("")) break;

            if(this.flag == 1)
            {
                this.MAX_SIZE = line.length();
                if(this.MAX_SIZE > this.MAX_ALLOWABLE_PUZZLE_SIZE)
                {
                    System.out.println("The puzzle entered is too large");
                    System.exit(0);
                }
                this.wordPuzzle = new char[this.MAX_SIZE][this.MAX_SIZE];
                this.flag = 0;
            }

            setPuzzleRow(line);

        }
    }



    private void setPuzzleRow(String fileLine)
    {
        char[] letters = fileLine.toCharArray();
        for(int i = 0; i < this.MAX_SIZE; i++)
            this.wordPuzzle[this.rowPosition][i] = letters[i];
        this.rowPosition++;
    }


    public void printPuzzle()
    {
        for(char[] row: this.wordPuzzle)
            printRow(row);
        System.out.println();
    }

    private void printRow(char[] row)
    {
        for(char c: row)
            System.out.print(c);

        System.out.println();
    }

    public int getPuzzleSize()
    {
        return this.MAX_SIZE;
    }

    public char getLetter(int row, int column)
    {
        return this.wordPuzzle[row][column];
    }


}
