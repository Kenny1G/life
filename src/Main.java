import model.BoardState;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        BoardState board = new BoardState(200,300);
        while (true)
        {
            board.render();
            board.setBoardState(board.nextBoardState());
            Thread.sleep(3);

        }


    }

}
