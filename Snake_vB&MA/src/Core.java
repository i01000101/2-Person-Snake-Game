
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Core implements ActionListener{
    
    JFrame window;

    StartScreen startScreen;
    SkorBoard skorBoard;
    Game gameScreen;
    
    int frameWidth, frameHeight, heightSkorBoard;
    
    public static void main(String[] args) {
        Core core = new Core();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
        // to get size of screen that in playing to create frame at middle as correct at every computer
        
        core.heightSkorBoard = 50;
        
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        
        core.frameWidth = 720;
        core.frameHeight = 480 + core.heightSkorBoard;
        
        core.window = new JFrame("Snake");
        core.window.setUndecorated(true);
        core.window.setLayout(null);
        core.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        core.window.setResizable(false);
        
        core.window.setBounds((int) ((screenWidth - core.frameWidth)/2),
                (int) ((screenHeight - core.frameHeight)/3), core.frameWidth, core.frameHeight);
        core.login();
        }

    public void login(){
        startScreen = new StartScreen(frameWidth, frameHeight);
        startScreen.setBounds(0, 0, frameWidth, frameHeight);
        startScreen.setVisible(true);
        
        window.add(startScreen);
        window.setVisible(true);
    
        startScreen.getClassicButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                window.remove(startScreen);
                setupGame();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        startScreen.getPeoplevsButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                window.remove(startScreen);
                setupGame();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        }
    
    public void setupGame(){
        skorBoard = new SkorBoard(frameWidth, startScreen.getIsClassic(), this);
        skorBoard.setBounds(0, 0, frameWidth, heightSkorBoard);
        
        gameScreen = new Game(frameWidth, frameHeight - heightSkorBoard,
                startScreen.getIsClassic(),skorBoard);
        gameScreen.changeSettings(startScreen.getIsAppleMoving(), startScreen.getIsThereWall(),
                startScreen.getIsThereObstacle());
        gameScreen.setBounds(0, heightSkorBoard, frameWidth, frameHeight-heightSkorBoard);
        
        window.add(skorBoard);
        window.add(gameScreen);
        gameScreen.requestFocusInWindow();
        window.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getID() == 666){
            window.remove(skorBoard);
            window.remove(gameScreen);
            window.repaint();
            setupGame();
        }
        if(e.getID() == 111){
            window.remove(startScreen);
            window.remove(skorBoard);
            window.remove(gameScreen);
            window.repaint();
            login();
        }
    }
}