package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public BoardState(String fileName)
    {
        setDimensionsFromFile(fileName);
        try {
            String line;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boardState = deadState();
            int y = 0;
            while ((line = bufferedReader.readLine()) != null)
            {
                for (int x = 0; x < line.length(); x++)
                {
                    int a = Integer.parseInt(String.valueOf(line.charAt(x)));
                    boardState[y][x] =  a;
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDimensionsFromFile(String fileName)
    {
        try
        {
            String line;
            int rows = 0;
            int columns = 0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader firstBufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = firstBufferedReader.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    columns++;
                }
                rows++;
            }
            columns = columns / rows;
            this.width = columns;
            this.height = rows;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
                    System.out.print("+");
                }else
                {
                    System.out.print("#");
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
                //todo: fix next BoardState bug.
                nextBoard[y][x] = nextState(thisBoard[y][x],numOfAliveNeighbours(y,x,thisBoard));
            }
        }
        return nextBoard;

    }

    public int numOfAliveNeighbours(int y, int x, int[][] state)
    {
        int numAlive = 0;
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
