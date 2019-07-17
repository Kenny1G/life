package model;

import java.util.Random;

public class BoardState
{
    private int width;
    private int height;
    private int[][] boardState;

    public BoardState(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.boardState = randomState();
    }

    private int[][] randomState()
    {
        int[][] newState = deadState();
        for (int x = 0; x < newState.length; x++)
        {
            for (int y = 0; y < newState[x].length; y++)
            {
                newState[x][y] = randomZeroOrOne();
            }
        }
        return newState;
    }
    public int[][] deadState()
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

    public void setBoardState(int[][] boardState)
    {
        this.boardState = boardState;
    }

    public BoardState(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public int[][] nextBoardState()
    {
        int[][] nextBoard = this.deadState();
        int[][] thisBoard = this.getBoardState();
        for (int y = 0; y < thisBoard.length; y++)
        {
            for ( int x = 0; x < thisBoard[y].length; x++)
            {
                nextBoard[y][x] = nextState(thisBoard[y][x],numOfAliveNeighbours(y,x,this));
            }
        }
        return nextBoard;

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

    public int nextState(int currentState, int numOfNeighbours)
    {
        if (currentState == 1)
        {
            if (numOfNeighbours == 2 || numOfNeighbours == 3)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
          if (numOfNeighbours == 3)
          {
              return 1;
          }
          else
          {
              return 0;
          }
        }
    }
}
