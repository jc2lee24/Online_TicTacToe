import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


public class ClientScreen extends JPanel implements MouseListener {
    private Game game;
    private ObjectOutputStream outObject;
    private ObjectInputStream inObject;
    private PushbackInputStream inPush;
    
    public ClientScreen() {
        this.setLayout(null);
        addMouseListener(this);

    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(400,300);
    }

    public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
        //draw background

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if(game == null){
            return;
        }

        int[][] board = game.getBoard();
        for(int i = 0;i<board.length;i++) {
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j] == 1) {
                    g.setColor(Color.RED);
                    g.fillRect(i*50,j*50,50,50);
                }
                else if(board[i][j] == 2) {
                    g.setColor(Color.BLUE);
                    g.fillRect(i*50,j*50,50,50);
                }
                else{
                    g.setColor(Color.WHITE);
                    g.fillRect(i*50,j*50,50,50);

                }
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(0,0,150,150);
                g.drawRect(0,0,150,150);
        g.drawLine(50,0,50,150);
        g.drawLine(100, 0, 100, 150);
        g.drawLine(0, 50, 150, 50);
        g.drawLine(0, 100, 150, 100);

        if(!game.checkWin().equals("none")){
            g.drawString(game.checkWin(),20,170);

            game.setTurn(50);
                try {
                    outObject.reset();
                    outObject.writeObject(game);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

        }else{
            g.drawString("Player "+game.getTurn()+" turn", 20, 170);

        }
    }

    public void mousePressed(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        
        int coord1 = x/50;
        int coord2 = y/50;

        if(coord1>2 || coord2>2){
            return;
        }

        game.updateBoard(coord1,coord2, 2);

        try {
            outObject.reset();
            outObject.writeObject(game);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();

        // send it over yo
    }
    public void poll() throws IOException{

		String hostName = "localhost"; 
		int portNumber = 1111;
		Socket serverSocket = new Socket(hostName, portNumber);
        outObject = new ObjectOutputStream(serverSocket.getOutputStream());
        inPush = new PushbackInputStream(serverSocket.getInputStream());
        inObject = new ObjectInputStream(serverSocket.getInputStream());

        while (true) {
            if(inPush.available() != 0){
                try {
                    
                    Object thing = inObject.readObject();
                    if(thing instanceof Game){
                        game = (Game)thing;
                        if(game.getTurn() == 50){
                            break;
                        }
                        repaint();
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                }
            }
        }

		//listens for inputs
		
	}


    





}