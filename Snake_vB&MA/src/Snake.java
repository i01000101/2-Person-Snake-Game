
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;


public class Snake{
    ArrayList <JLabel> snakeSquares;
    
    private int skor, speedValue, headX, headY, widthSnake, direction, mapWidth, mapHeight;
    /*
     * Direction 1: Up
     * Direction 2: Right
     * Direction 3: Down
     * Direction 4: Left
     */
    
    private final boolean isGreen;
    
    private ImageIcon rightHead, leftHead, upHead, downHead, horizontalBody, verticalBody,
            rightTail, leftTail, upTail, downTail, corner1, corner2, corner3, corner4;
    
    private Timer speedTimer;

    public Snake(int mapWidth, int mapHeight ,int speedValue, int headX, int headY, int widthSnake,
            boolean isGreen, ActionListener actionListener) {
        
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.speedValue = speedValue;
        this.headX = headX;
        this.headY = headY;
        this.widthSnake = widthSnake;
        this.isGreen = isGreen;
        
        this.skor = 0;
        this.direction = 2;
        
        createImages(isGreen); // creating icons of snake's part with snake's color
        
        snakeSquares = new ArrayList<JLabel>();
        
        createSnake();
        
        speedTimer = new Timer(speedValue,actionListener);
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public int getSpeedValue() {
        return speedValue;
    }

    public void setSpeedValue(int speedValue) {
        if(speedValue >= 60){this.speedValue = speedValue;}
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Timer getSpeedTimer() {
        return speedTimer;
    }

    public void setSpeedTimer(Timer speedTimer) {
        this.speedTimer = speedTimer;
    }
    
    private void createImages(boolean isGreen){
        rightHead = new ImageIcon(isGreen?"images/rightHeadG.png":"images/rightHeadR.png");
        leftHead = new ImageIcon(isGreen?"images/leftHeadG.png":"images/leftHeadR.png");
        upHead = new ImageIcon(isGreen?"images/upHeadG.png":"images/upHeadR.png");
        downHead = new ImageIcon(isGreen?"images/downHeadG.png":"images/downHeadR.png");
        rightTail = new ImageIcon(isGreen?"images/rightTailG.png":"images/rightTailR.png");
        leftTail = new ImageIcon(isGreen?"images/leftTailG.png":"images/leftTailR.png");
        upTail = new ImageIcon(isGreen?"images/upTailG.png":"images/upTailR.png");
        downTail = new ImageIcon(isGreen?"images/downTailG.png":"images/downTailR.png");
        corner1 = new ImageIcon(isGreen?"images/corner1G.png":"images/corner1R.png");
        corner2 = new ImageIcon(isGreen?"images/corner2G.png":"images/corner2R.png");
        corner3 = new ImageIcon(isGreen?"images/corner3G.png":"images/corner3R.png");
        corner4 = new ImageIcon(isGreen?"images/corner4G.png":"images/corner4R.png");
        
        horizontalBody = new ImageIcon(isGreen?"images/horizontalBodyG.png":"images/horizontalBodyR.png");
        verticalBody = new ImageIcon(isGreen?"images/verticalBodyG.png":"images/verticalBodyR.png");
    }
    
    private void createSnake(){
        // Creating first parts of snake
        snakeSquares.add(new JLabel(new ImageIcon(rightHead.getImage().getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT))));
        snakeSquares.get(0).setBounds(headX, headY, widthSnake, widthSnake);
        snakeSquares.add(new JLabel(new ImageIcon(horizontalBody.getImage().getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT))));
        snakeSquares.get(1).setBounds(headX - widthSnake, headY, widthSnake, widthSnake);
        snakeSquares.add(new JLabel(new ImageIcon(rightTail.getImage().getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT))));
        snakeSquares.get(2).setBounds(headX - 2*widthSnake, headY, widthSnake, widthSnake);
    }

    
    public void grow(){
        JLabel newSquare = new JLabel();
        int x = snakeSquares.get(snakeSquares.size()-1).getX(),
                y = snakeSquares.get(snakeSquares.size()-1).getY();
        newSquare.setBounds(x, y, widthSnake, widthSnake);
        this.move();
        snakeSquares.add(newSquare);
        imageUpdater();
    }
    
    public void move(){
        
            for(int i = snakeSquares.size() - 1; 0 < i; i--){// for move of boddy's part
                snakeSquares.get(i).
                        setLocation(snakeSquares.get(i - 1).getX(), snakeSquares.get(i - 1).getY());
            }
            // for head's move
            if(direction == 1){
                snakeSquares.get(0).
                        setLocation(snakeSquares.get(0).getX(), snakeSquares.get(0).getY() - widthSnake);
            }
            if(direction == 3){
                snakeSquares.get(0).
                        setLocation(snakeSquares.get(0).getX(), snakeSquares.get(0).getY() + widthSnake);
            }
            if(direction == 2){
                snakeSquares.get(0).
                        setLocation(snakeSquares.get(0).getX() + widthSnake, snakeSquares.get(0).getY());
            }
            if(direction == 4){
                snakeSquares.get(0).
                        setLocation(snakeSquares.get(0).getX() - widthSnake, snakeSquares.get(0).getY());
            }   
        imageUpdater();
    }
    
    public void imageUpdater(){
        // for head
        if(direction == 3){
            snakeSquares.get(0).setIcon(new ImageIcon(downHead.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
        }
        if(direction == 1){
            snakeSquares.get(0).setIcon(new ImageIcon(upHead.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
        }
        if(direction == 2){
            snakeSquares.get(0).setIcon(new ImageIcon(rightHead.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
        }
        if(direction == 4){
            snakeSquares.get(0).setIcon(new ImageIcon(leftHead.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
        }
        
        // for tail
        if(Math.abs(snakeSquares.get(snakeSquares.size() - 1).getX() - 
                snakeSquares.get(snakeSquares.size() - 2).getX() ) <= widthSnake &&
                Math.abs(snakeSquares.get(snakeSquares.size() - 1).getY() - 
                snakeSquares.get(snakeSquares.size() - 2).getY() ) <= widthSnake){
            if(snakeSquares.get(snakeSquares.size() - 1).getY() >
                    snakeSquares.get(snakeSquares.size() - 2).getY()){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(upTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(snakeSquares.size() - 1).getY() <
                    snakeSquares.get(snakeSquares.size() - 2).getY()){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(downTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(snakeSquares.size() - 1).getX() >
                    snakeSquares.get(snakeSquares.size() - 2).getX()){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(leftTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(snakeSquares.size() - 1).getX() <
                    snakeSquares.get(snakeSquares.size() - 2).getX()){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(rightTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
        }else{
            if(snakeSquares.get(snakeSquares.size() - 1).getY() == 0 &&
                    snakeSquares.get(snakeSquares.size() - 2).getY() == mapHeight - widthSnake){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(upTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(snakeSquares.size() - 1).getY() == mapHeight - widthSnake &&
                    snakeSquares.get(snakeSquares.size() - 2).getY() == 0){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(downTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(snakeSquares.size() - 1).getX() == 0 &&
                    snakeSquares.get(snakeSquares.size() - 2).getX() == mapWidth - widthSnake){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(leftTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(snakeSquares.size() - 1).getX() == mapWidth - widthSnake &&
                    snakeSquares.get(snakeSquares.size() - 2).getX() == 0){
                snakeSquares.get(snakeSquares.size() - 1).setIcon(new ImageIcon(rightTail.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
        }
        //for body
        for(int i = 1; i < snakeSquares.size() - 1; i++){
                    if(snakeSquares.get(i - 1).getY() != snakeSquares.get(i).getY() &&
                            snakeSquares.get(i - 1).getX() == snakeSquares.get(i).getX()){
                        snakeSquares.get(i).setIcon(new ImageIcon(verticalBody.getImage().
                            getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
                    }
                    if(snakeSquares.get(i - 1).getX() != snakeSquares.get(i).getX() &&
                            snakeSquares.get(i - 1).getY() == snakeSquares.get(i).getY()){
                        snakeSquares.get(i).setIcon(new ImageIcon(horizontalBody.getImage().
                            getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
                    }
            //for corners
            if((Math.abs(snakeSquares.get(i).getX() - 
                snakeSquares.get(i-1).getX() ) <= widthSnake &&
                Math.abs(snakeSquares.get(i).getY() - 
                snakeSquares.get(i-1).getY() ) <= widthSnake) && 
                    (Math.abs(snakeSquares.get(i).getX() - 
                snakeSquares.get(i+1).getX() ) <= widthSnake &&
                Math.abs(snakeSquares.get(i).getY() - 
                snakeSquares.get(i+1).getY() ) <= widthSnake)){
                
                if(((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i + 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                        ((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i - 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX()))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner1.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
                }
                if(((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i + 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                        ((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i - 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX()))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner2.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
                } 
                if(((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i + 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                        ((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i - 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX()))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner3.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
                }            
                if(((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i + 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                        ((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i - 1).getX() &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX()))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner4.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
                }
            }else{
            if(snakeSquares.get(i).getY() == mapHeight - widthSnake &&
                    ((( snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i + 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                    (snakeSquares.get(i - 1).getY() == 0 &&
                    snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                    ((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i - 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                    (snakeSquares.get(i + 1).getY() == 0 && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){
                
                snakeSquares.get(i).setIcon(new ImageIcon(corner1.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getX() == 0 &&
                    (((snakeSquares.get(i + 1).getX() == mapWidth - widthSnake &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) ||
                        ((snakeSquares.get(i - 1).getX() == mapWidth - widthSnake &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner1.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getY() == 0 &&
                    (((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i + 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                    (snakeSquares.get(i - 1).getY() == mapHeight - widthSnake && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                    ((snakeSquares.get(i).getX() - widthSnake == snakeSquares.get(i - 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                    (snakeSquares.get(i + 1).getY() == mapHeight - widthSnake && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){
                
                snakeSquares.get(i).setIcon(new ImageIcon(corner2.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getX() == 0 &&
                    (((snakeSquares.get(i + 1).getX() == mapWidth - widthSnake &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) ||
                        ((snakeSquares.get(i - 1).getX() == mapWidth - widthSnake &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner2.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getY() == 0 &&
                    (((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i + 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                    (snakeSquares.get(i - 1).getY() == mapHeight - widthSnake && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                    ((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i - 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                    (snakeSquares.get(i + 1).getY() == mapHeight - widthSnake && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){
                
                snakeSquares.get(i).setIcon(new ImageIcon(corner3.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getX() == mapWidth - widthSnake &&
                    (((snakeSquares.get(i + 1).getX() == 0 &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) ||
                        ((snakeSquares.get(i - 1).getX() == 0 &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() - widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner3.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getY() == mapHeight - widthSnake && 
                    (((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i + 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                    (snakeSquares.get(i - 1).getY() == 0 && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) || 
                    ((snakeSquares.get(i).getX() + widthSnake == snakeSquares.get(i - 1).getX() &&
                    snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                    (snakeSquares.get(i + 1).getY() == 0 && 
                    snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){
                
                snakeSquares.get(i).setIcon(new ImageIcon(corner4.getImage().
                    getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            if(snakeSquares.get(i).getX() == mapWidth - widthSnake &&
                    (((snakeSquares.get(i + 1).getX() == 0 &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i + 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i - 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i - 1).getX())) ||
                        ((snakeSquares.get(i - 1).getX() == 0 &&
                        snakeSquares.get(i).getY() == snakeSquares.get(i - 1).getY())&& 
                        (snakeSquares.get(i).getY() + widthSnake == snakeSquares.get(i + 1).getY() && 
                        snakeSquares.get(i).getX() == snakeSquares.get(i + 1).getX())))){

                    snakeSquares.get(i).setIcon(new ImageIcon(corner4.getImage().
                        getScaledInstance(widthSnake, widthSnake, Image.SCALE_DEFAULT)));
            }
            }
        }
    }
}