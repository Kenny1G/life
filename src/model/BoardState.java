import java.util.Arrays;
import java.util.Random;

public class BoardState
{
    private int[][] boardState;
    public BoardState(int width, int height)
    {
        this.boardState = randomState(width, height);
    }

    private int[][] randomState(int width, int height)
    {
        int[][] newState = deadState(width,height);
        for (int x = 0; x < newState.length; x++)
        {
            for (int y = 0; y < newState[x].length; y++)
            {
                newState[x][y] = randomZeroOrOne();
            }
        }
        return newState;
    }
    private int[][] deadState(int width, int height)
    {
        int[][] grid = new int[height][width];
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                grid[y][x] = 0;
            }
        }
        return grid;
    }

    private int randomZeroOrOne()
    {
        Random r = new Random();
        return r.nextInt(2);
    }

    public void render()
    {
        System.out.println();
        for (int[] row : boardState)
        {
            System.out.print("|");
            for (int i: row)
            {
                if(i == 0)
                {
                    System.out.print(" ");
                }else
                {
                    System.out.print("$");
                }
            }
            System.out.println("|");
        }
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public int[][] nextBoardState(BoardState currentBoardState)
    {
        return null;

    }

    public int numOfAliveNeighbours(int y, int x, BoardState boardState)
    {
        int numAlive = 0;
        int[][] state = boardState.getBoardState();
        for (int yy = 0; yy < state.length; yy++)
        {
            if (yy == y || yy == y - 1 || yy == y+ 1)
            {
                for (int xx = 0; xx < state[yy].length; xx++)
                {
                    if (xx == x || xx == x - 1 || xx == x + 1)
                    {
                        if (state[yy][xx] == 1)
                        {
                            numAlive++;
                        }
                    }
                }
            }

        }
        return numAlive;
    }
}
