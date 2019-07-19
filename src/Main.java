import model.BoardState;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        BoardState board = new BoardState("/home/kenny/Documents/life/src/Files/toad.txt");
        while (true)
        {
            board.render();
            board.setBoardState(board.nextBoardState());
            Thread.sleep(3);

        }


    }

}
