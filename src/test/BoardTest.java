package test;

import model.BoardState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest
{
    private BoardState boardState = new BoardState(3,3);
    int[][] deadState;
    int[][] oneState;
    int[][] twoState;

    @Before
    public void setup()
    {
        deadState = boardState.deadState();
        oneState = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,1}
        };
        twoState = new int[][]{
                {0,0,1},
                {0,1,1},
                {0,0,0}
        };
    }

    @Test
    public void testAliveMethod1()
    {
        boardState.setBoardState(deadState);
        int check = boardState.numOfAliveNeighbours(0,0,boardState.getBoardState());
        assertEquals(check,0);

    }

    @Test
    public void testAliveMethod2()
    {
        boardState.setBoardState(oneState);
        int check = boardState.numOfAliveNeighbours(0,0,boardState.getBoardState());
        assertEquals(check,1);
    }

    @Test
    public void testAliveMethod3()
    {
        boardState.setBoardState(oneState);
        int check = boardState.numOfAliveNeighbours(1,1,boardState.getBoardState());
        assertEquals(check,2);
    }

    @Test
    public void testNextState1()
    {
        boardState.setBoardState(deadState);
        int[][] next = boardState.nextBoardState();
        assertArrayEquals(next, deadState);
    }

    @Test
    public void testNextState2()
    {
        boardState.setBoardState(twoState);
        int[][] next = boardState.nextBoardState();
        assertArrayEquals(next,new int[][]{
                {0,1,1},
                {0,1,1},
                {0,0,0}
        });
    }
}
