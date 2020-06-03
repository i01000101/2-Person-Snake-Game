
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener{
    
    private int width, height, widthSnake = 20;
    
    SkorBoard skorBoard;
    
    JLabel backGround, apple, announcement;
    
    private boolean isClassic, isAppleMoving, isThereWall, isThereObstacle;
    private ArrayList<Boolean> pressWait;
    Random random = new Random();
    
    Timer appleTimer = new Timer(5000,this);
    
    ArrayList<Snake> snakes;
    ArrayList<Snake> ghostSnakes;
    // Ghost snakes for that snake doesn't enter inside of other objects when real snake hit to anywhere
    // just cosmetic
    
    ArrayList<JLabel> obstacles;
    
    public Game(int width, int height, boolean isClassic, SkorBoard skorBoard) {
        super();
        this.setLayout(null);
        this.setFocusable(true);
        
        this.width = width;
        this.height = height;
        this.isClassic = isClassic;
        this.skorBoard = skorBoard;
        
        backGround = new JLabel(new ImageIcon(new ImageIcon("images/background.jpg").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        backGround.setBounds(0, 0, width, height);
        
        announcement = new JLabel();
        add(announcement);
        snakes = new ArrayList<>();
        ghostSnakes = new ArrayList<>();
        pressWait = new ArrayList<>();
        obstacles = new ArrayList<>();
        
        snakes.add(new Snake(width, height, 140,100,
                isClassic?(height/2):widthSnake*6,widthSnake,true, this));
        ghostSnakes.add(new Snake(width, height, 140,100,
                isClassic?(height/2):widthSnake*6,widthSnake,true, this));
        pressWait.add(false);
        
        // if game mod is 1 vs 1 we need one more sneak
        if(!isClassic){
            snakes.add(new Snake(width, height, 140,100,
                height-7*widthSnake,widthSnake,false, this));
            ghostSnakes.add(new Snake(width, height, 140,100,
                height-7*widthSnake,widthSnake,false, this));
            pressWait.add(false);
        }
        
        for(int i = 0; i < snakes.size(); i++){
            for(int j = 0; j < snakes.get(i).snakeSquares.size(); j++){
                add(snakes.get(i).snakeSquares.get(j));
            }
        snakes.get(i).getSpeedTimer().start();// when all parts of snake loaded, start timer
        ghostSnakes.get(i).getSpeedTimer().start();
        }
        apple = new JLabel(new ImageIcon(new ImageIcon("images/apple.png").getImage().getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
        addApple();
        add(apple);
        add(backGround);
        appleTimer.start();
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                //test
                if(!pressWait.get(0)){
                    if(e.getKeyCode() == e.VK_W && snakes.get(0).getDirection() != 3 &&
                            snakes.get(0).getDirection() != 1){
                        snakes.get(0).setDirection(1);
                        ghostSnakes.get(0).setDirection(1);
                        pressWait.set(0,true);
                    }
                    if(e.getKeyCode() == e.VK_S && snakes.get(0).getDirection() != 1 &&
                            snakes.get(0).getDirection() != 3){
                        snakes.get(0).setDirection(3);
                        ghostSnakes.get(0).setDirection(3);
                        pressWait.set(0,true);
                    }
                    if(e.getKeyCode() == e.VK_D && snakes.get(0).getDirection() != 4 &&
                            snakes.get(0).getDirection() != 2){
                        snakes.get(0).setDirection(2);
                        ghostSnakes.get(0).setDirection(2);
                        pressWait.set(0,true);
                    }
                    if(e.getKeyCode() == e.VK_A && snakes.get(0).getDirection() != 2 &&
                            snakes.get(0).getDirection() != 4){
                        snakes.get(0).setDirection(4);
                        ghostSnakes.get(0).setDirection(4);
                        pressWait.set(0,true);
                    }
                }
                if(!pressWait.get(snakes.size()-1)){
                    // for second(if there is) snake move way commands
                    if(e.getKeyCode() == e.VK_UP && snakes.get(snakes.size()-1).getDirection() != 3 &&
                            snakes.get(snakes.size()-1).getDirection() != 1){
                        snakes.get(snakes.size()-1).setDirection(1);
                        ghostSnakes.get(snakes.size()-1).setDirection(1);
                        pressWait.set(snakes.size()-1,true);
                    }
                    if(e.getKeyCode() == e.VK_DOWN && snakes.get(snakes.size()-1).getDirection() != 1 &&
                            snakes.get(snakes.size()-1).getDirection() != 3){
                        snakes.get(snakes.size()-1).setDirection(3);
                        ghostSnakes.get(snakes.size()-1).setDirection(3);
                        pressWait.set(snakes.size()-1,true);
                    }
                    if(e.getKeyCode() == e.VK_RIGHT && snakes.get(snakes.size()-1).getDirection() != 4 &&
                            snakes.get(snakes.size()-1).getDirection() != 2){
                        snakes.get(snakes.size()-1).setDirection(2);
                        ghostSnakes.get(snakes.size()-1).setDirection(2);
                        pressWait.set(snakes.size()-1,true);
                    }
                    if(e.getKeyCode() == e.VK_LEFT && snakes.get(snakes.size()-1).getDirection() != 2 &&
                            snakes.get(snakes.size()-1).getDirection() != 4){
                        snakes.get(snakes.size()-1).setDirection(4);
                        ghostSnakes.get(snakes.size()-1).setDirection(4);
                        pressWait.set(snakes.size()-1,true);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                e.consume();
            }
        });
    }
    
    public void changeSettings(boolean isAppleMoving, boolean isThereWall, boolean isThereObstacle){
        this.isAppleMoving = isAppleMoving;
        this.isThereWall = isThereWall;
        this.isThereObstacle = isThereObstacle;
        if(!isAppleMoving){
            appleTimer.stop();
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(isThereWall){
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            
            g2.setColor(new Color(0,100,0));
            g2.setStroke(new java.awt.BasicStroke(6));
            g2.drawRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < snakes.size(); i++){
            if(e.getSource() == snakes.get(i).getSpeedTimer()){
                ghostSnakes.get(i).move();
                
                int hitSituation = hitControl(ghostSnakes.get(i));
                
                if(hitSituation == 1){ // snake ate apple 
                    snakes.get(i).setSkor(snakes.get(i).getSkor() + 5);
                    
                    if(isClassic && snakes.get(0).getSkor() > skorBoard.getRecord()){
                        skorBoard.setRecord(snakes.get(0).getSkor());
                    }

                    skorBoard.skorLabels.get(i).setText(Integer.toString(snakes.get(i).getSkor()));
                    skorBoard.skorLabels.get(0)
                            .setLocation((isClassic?(width/2)-50:(width/2 - 50)/2+30)-
                                    (skorBoard.skorLabels.get(0).getText().length()-1)*5,
                            skorBoard.skorLabels.get(0).getY());// only cosmetic, bad
                    
                    snakes.get(i).setSpeedValue(snakes.get(i).getSpeedValue() - 10);
                    snakes.get(i).setSpeedTimer(new Timer(snakes.get(i).getSpeedValue(),this));
                    snakes.get(i).getSpeedTimer().start();
                    snakes.get(i).grow();
                    
                    ghostSnakes.get(i).setSpeedValue(snakes.get(i).getSpeedValue()-10);
                    ghostSnakes.get(i).setSpeedTimer(new Timer(snakes.get(i).getSpeedValue(),this));
                    ghostSnakes.get(i).getSpeedTimer().start();
                    ghostSnakes.get(i).grow();
                    
                    add(snakes.get(i).snakeSquares.get(snakes.get(i).snakeSquares.size() - 1));
                    add(backGround);
                    addApple();
                    if(isAppleMoving){appleTimer.stop();appleTimer.start();}
                }
                if(hitSituation == 2){
                    
                    snakes.get(i).move();
                    if(ghostSnakes.get(i).snakeSquares.get(0).getX() == -widthSnake ||
                            ghostSnakes.get(i).snakeSquares.get(0).getX() == this.getWidth()){
                        ghostSnakes.get(i).snakeSquares.get(0).setLocation
                        (Math.floorMod(snakes.get(i).snakeSquares.get(0).getX(),this.getWidth()),
                                snakes.get(i).snakeSquares.get(0).getY());
                    }
                    if(ghostSnakes.get(i).snakeSquares.get(0).getY() == -widthSnake ||
                            ghostSnakes.get(i).snakeSquares.get(0).getY() == this.getHeight()){
                        ghostSnakes.get(i).snakeSquares.get(0).setLocation
                        (snakes.get(i).snakeSquares.get(0).getX(),
                        Math.floorMod(snakes.get(i).snakeSquares.get(0).getY(),this.getHeight()));
                    }
                    
                        snakes.get(i).snakeSquares.get(0).setLocation
                        (ghostSnakes.get(i).snakeSquares.get(0).getLocation());
                        
                }
                if(hitSituation == -1){
                    if(isThereWall){
                        if(!isClassic){// to punish the death snake
                            snakes.get(i).setSkor(snakes.get(i).getSkor() - 50);
                            skorBoard.skorLabels.get(i).setText(Integer.toString(snakes.get(i).getSkor()));
                            skorBoard.skorLabels.get(i).setForeground(Color.red);
                        }
                        gameOver();
                    }
                }
                if(hitSituation == -2){
                    if(!isClassic){// to punish the death snake
                        snakes.get(i).setSkor(snakes.get(i).getSkor() - 50);
                        skorBoard.skorLabels.get(i).setText(Integer.toString(snakes.get(i).getSkor()));
                        skorBoard.skorLabels.get(i).setForeground(Color.red);
                    }
                    gameOver();
                    
                }
                if(snakes.size() == 2){
                    if(hitControl(ghostSnakes.get(i), ghostSnakes.get(Math.abs(i-1))) == -3){
                        snakes.get(i).setSkor(snakes.get(i).getSkor() - 50);// to punish the death snake
                        skorBoard.skorLabels.get(i).setText(Integer.toString(snakes.get(i).getSkor()));
                        skorBoard.skorLabels.get(i).setForeground(Color.red);
                        gameOver();
                    }
                }
                if(hitSituation == -4){
                        if(!isClassic){// to punish the death snake
                            snakes.get(i).setSkor(snakes.get(i).getSkor() - 50);
                            skorBoard.skorLabels.get(i).setText(Integer.toString(snakes.get(i).getSkor()));
                            skorBoard.skorLabels.get(i).setForeground(Color.red);
                        }
                        gameOver();
                    
                }
                if(hitSituation != -3 && hitSituation != -2 && hitSituation != -1 && hitSituation != 2 && 
                        hitSituation != -4){
                    snakes.get(i).move();
                    pressWait.set(i,false);
                }
                
            }
        }
        //test
        if(e.getSource() == appleTimer){
            addApple();
        }
        
        repaint();
    }
    int hitControl(Snake snake){
        
        int headX = snake.snakeSquares.get(0).getX(), headY = snake.snakeSquares.get(0).getY();
        int headCenterX = snake.snakeSquares.get(0).getX() + (widthSnake/2),
                headCenterY = snake.snakeSquares.get(0).getY() + (widthSnake/2);
        
        // did snake eat apple
        if(apple.getX() < headCenterX && headCenterX < apple.getX() + widthSnake &&
                    apple.getY() < headCenterY && headCenterY < apple.getY() + widthSnake){
        return 1;
        }
        
        // did snake hit to walls
        if(!(0 <= headX && headX + widthSnake <= this.getWidth() && 0 <= headY &&
                headY + widthSnake <= this.getHeight())){
            return isThereWall?-1:2; 
        }
        // did snake hit to itself
        for(int i = 2; i < snake.snakeSquares.size(); i++){
            if((snake.snakeSquares.get(i).getX() < headCenterX &&
                    headCenterX < snake.snakeSquares.get(i).getX() + widthSnake &&
                    snake.snakeSquares.get(i).getY() < headCenterY &&
                    headCenterY < snake.snakeSquares.get(i).getY() + widthSnake)){
                  
            return -2; 
            }
        }
        for(int i = 0; i < obstacles.size(); i++){
            if(obstacles.get(i).getX() < headCenterX && headCenterX < obstacles.get(i).getX() + widthSnake &&
                        obstacles.get(i).getY() < headCenterY && headCenterY < obstacles.get(i).getY() + widthSnake){
            return -4;
            }
        }
        return 0;
    }
    int hitControl(Snake snake1, Snake snake2){
        int headCenterX = snake1.snakeSquares.get(0).getX() + (widthSnake/2),
                headCenterY = snake1.snakeSquares.get(0).getY() + (widthSnake/2);
        
        for(int i = 0; i < snake2.snakeSquares.size(); i++){
            if((snake2.snakeSquares.get(i).getX() < headCenterX &&
                    headCenterX < snake2.snakeSquares.get(i).getX() + widthSnake &&
                    snake2.snakeSquares.get(i).getY() < headCenterY &&
                    headCenterY < snake2.snakeSquares.get(i).getY() + widthSnake)){
                
            return -3; 
            }
        }
        return 0;
    }
    
    void addApple(){
        boolean inAnything;
        do{
            inAnything = false;
            apple.setVisible(false);
            apple.setBounds(widthSnake*random.nextInt(width/widthSnake),
                widthSnake*random.nextInt(height/widthSnake), widthSnake, widthSnake);
            inAnything = false;
            for(int i = 0; i < snakes.size(); i++){
                for(int j = 0; j < snakes.get(i).snakeSquares.size(); j++){
                    if(apple.getX() == snakes.get(i).snakeSquares.get(j).getX() &&
                            apple.getY() == snakes.get(i).snakeSquares.get(j).getY()){
                        inAnything = true;
                        break;
                    }
                }
            }
            for(int i = 0; i < obstacles.size(); i++){
                if(Math.abs(apple.getX() - obstacles.get(i).getX()) <= widthSnake
                        && Math.abs(apple.getY() - obstacles.get(i).getY()) <= widthSnake){
                    inAnything = true;
                    break;
                }
            }
        }while(inAnything || apple.getX() == 0 || apple.getY() == 0 || apple.getX() == width - widthSnake
                || apple.getY() == height - widthSnake);// to obstruct a bug
        
        apple.setVisible(true);
        if(isThereObstacle){addObstacle();}
    }
    
    void addObstacle(){
        obstacles.add(new JLabel(new ImageIcon(new ImageIcon("images/obstacle.png").getImage().
                getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT))));
        boolean inAnything;
        do{
            inAnything = false;
            obstacles.get(obstacles.size() - 1).setBounds(widthSnake*random.nextInt(width/widthSnake),
                widthSnake*random.nextInt(height/widthSnake), widthSnake, widthSnake);
            inAnything = false;
            for(int i = 0; i < snakes.size(); i++){
                for(int j = 0; j < snakes.get(i).snakeSquares.size(); j++){
                    if(obstacles.get(obstacles.size() - 1).getX() == snakes.get(i).snakeSquares.get(j).getX() &&
                            obstacles.get(obstacles.size() - 1).getY()
                            == snakes.get(i).snakeSquares.get(j).getY()){
                        inAnything = true;
                        break;
                    }
                }
            }
            if(Math.abs(apple.getX() - obstacles.get(obstacles.size() - 1).getX()) <= widthSnake
                        && Math.abs(apple.getY() - obstacles.get(obstacles.size() - 1).getY()) <= widthSnake){
                    inAnything = true;
                    break;
            }
        }while(inAnything || obstacles.get(obstacles.size() - 1).getX() == 0 ||
                obstacles.get(obstacles.size() - 1).getY() == 0 ||
                obstacles.get(obstacles.size() - 1).getX() == this.getWidth() - widthSnake
                || obstacles.get(obstacles.size() - 1).getY() == this.getHeight() - widthSnake);
        add(obstacles.get(obstacles.size() - 1));
        obstacles.get(obstacles.size() - 1).setVisible(true);
        add(backGround);
    }
    
    void gameOver(){
        stopper();
        if(isClassic){
            announcement.setText("THERE IS NO MORE FUTURE FOR YOU!");
        }else{
            skorBoard.skorLabels.get(0)
                        .setLocation(((width/2 - 50)/2+30)-
                                (skorBoard.skorLabels.get(0).getText().length()-1)*5,
                        skorBoard.skorLabels.get(0).getY());// only cosmetic, bad
            if(snakes.get(0).getSkor() != snakes.get(1).getSkor()){
                announcement.setText("WINNER WINNER CHICKEN DINNER FOR \n" + (snakes.get(0).getSkor() >
                    snakes.get(1).getSkor()?"GREEN":"RED"));
            }else{
                announcement.setText("INTERESTING!");
            }
        }
        announcement.setFont(new Font("Arial", Font.BOLD, 18));
        announcement.setHorizontalAlignment(JLabel.CENTER);
        announcement.setForeground(Color.WHITE);
        announcement.setBackground(Color.black);
        announcement.setOpaque(true);
        announcement.setBounds((this.width - 500)/2,
                (this.height + skorBoard.getHeight() - 200)/2, 500, 100);
    }
    void stopper(){
        appleTimer.stop();
        for(int i = 0; i < snakes.size(); i++){
            snakes.get(i).getSpeedTimer().stop();
            ghostSnakes.get(i).getSpeedTimer().stop();
        }
    }
}