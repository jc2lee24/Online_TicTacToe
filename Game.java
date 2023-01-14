import java.io.Serializable;
public class Game implements Serializable{
    private int[][] board;
    private int turn;
    public Game(){
        board = new int[3][3];
        turn = 1;
    }


    public void updateBoard(int i, int j, int player){
        if(turn==player){

            this.board[i][j] = player;
            if(turn==1){
                turn = 2;
            }else{
                turn = 1;
            }
        }
    }
    public int getCoord(int i, int j){
        return this.board[i][j];
    }
    public int[][] getBoard(){
        return this.board;
    }
    public void setTurn(int turn){
        this.turn = turn;
    }
    public int getTurn(){
        return turn;
    }
    
    public String checkWin(){
        // check if a tic tac board is valid;
        // check for a tie
        
        if(this.board[0][0] == this.board[0][1] && this.board[0][1] == this.board[0][2] && this.board[0][0] != 0){
            return "Player " + this.board[0][0] + " wins!";
        }
        if(this.board[1][0] == this.board[1][1] && this.board[1][1] == this.board[1][2] && this.board[1][0] != 0){
            return "Player " + this.board[1][0] + " wins!";
        }
        if(this.board[2][0] == this.board[2][1] && this.board[2][1] == this.board[2][2] && this.board[2][0] != 0){
            return "Player " + this.board[2][0] + " wins!";
        }
        if(this.board[0][0] == this.board[1][0] && this.board[1][0] == this.board[2][0] && this.board[0][0] != 0){
            return "Player " + this.board[0][0] + " wins!";
        }
        if(this.board[0][1] == this.board[1][1] && this.board[1][1] == this.board[2][1] && this.board[0][1] != 0){
            return "Player " + this.board[0][1] + " wins!";
        }
        if(this.board[0][2] == this.board[1][2] && this.board[1][2] == this.board[2][2] && this.board[0][2] != 0){
            return "Player " + this.board[0][2] + " wins!";
        }
        if(this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[0][0] != 0){
            return "Player " + this.board[0][0] + " wins!";
        }
        if(this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0] && this.board[0][2] != 0){
            return "Player " + this.board[0][2] + " wins!";
        }
        boolean filled = true;
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j]==0){
                    filled = false;
                }
            }
        }
        if(filled){
            return "Tie!";
        }


        return "none";
    }
    
}
