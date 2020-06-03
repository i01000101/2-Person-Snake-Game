
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartScreen extends JPanel implements ActionListener {
    
    private int width, height;
    
    private JLabel background, settings, appleText, wallsText, obstacleText, signature;
    
    private EButton classicButton, peoplevsButton;
    private ESwitch forApple, forWall, forObstacle;
    
    private boolean isStart = false, isClassic, isAppleMoving = false,
            isThereWall = false, isThereObstacle = false;

    public boolean getIsClassic() {
        return isClassic;
    }


    public StartScreen(int width, int height) {
        super();
        this.setLayout(null);
        
        this.width = width;
        this.height = height;
        
        classicButton = new EButton(this, new ImageIcon("images/classicButton.png"),
                (width/2 - 150)/2, (height - 50)/2, 150, 50, 1.05);
        
        this.add(classicButton);
        
        peoplevsButton = new EButton(this, new ImageIcon("images/1vs1.png"),
                width/2 +(width/2 - 150)/2, (height - 50)/2, 150, 50, 1.05);
        
        this.add(peoplevsButton);
        
        // settings part
        
        settings = new JLabel(new ImageIcon(new ImageIcon("images/settings.png").
                getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        settings.setBounds((width-30)/2, ((height-30)/2)-150, 30, 30);
        this.add(settings);
        
        appleText = new JLabel("Moving Apple");
        appleText.setBounds((width-75)/2, ((height-30)/2)-100, 160, 30);
        appleText.setFont(new Font("Arial", Font.BOLD, 12));
        appleText.setForeground(Color.LIGHT_GRAY);
        this.add(appleText);
        
        forApple = new ESwitch(this, new ImageIcon("images/switchBar.png"), (width-75)/2,
                ((height-30)/2)-70, 75, 30, 10);
        forApple.setSelectionImage(new ImageIcon("images/rightHeadR.png"), 20, 20, 10, 5);
        this.add(forApple);
        
        wallsText = new JLabel("Walls");
        wallsText.setBounds((width-35)/2, ((height-30)/2)-30, 35, 30);
        wallsText.setFont(new Font("Arial", Font.BOLD, 12));
        wallsText.setForeground(Color.LIGHT_GRAY);
        this.add(wallsText);
        
        forWall = new ESwitch(this, new ImageIcon("images/switchBar.png"), 
                (width-75)/2, (height-30)/2, 75, 30, 10);
        forWall.setSelectionImage(new ImageIcon("images/rightHeadR.png"), 20, 20, 10, 5);
        this.add(forWall);
        
        obstacleText = new JLabel("Obstacles");
        obstacleText.setBounds((width-60)/2, ((height-30)/2)+40, 60, 30);
        obstacleText.setFont(new Font("Arial", Font.BOLD, 12));
        obstacleText.setForeground(Color.LIGHT_GRAY);
        this.add(obstacleText);
        
        forObstacle = new ESwitch(this, new ImageIcon("images/switchBar.png"), 
                (width-75)/2, ((height-30)/2)+70, 75, 30, 10);
        forObstacle.setSelectionImage(new ImageIcon("images/rightHeadR.png"), 20, 20, 10, 5);
        this.add(forObstacle);
        
        signature = new JLabel("created by ÜNLÜTÜRK");
        signature.setBounds((width-125)/2, ((height-30)/2)+150, 125, 30);
        signature.setFont(new Font("Arial", Font.BOLD, 12));
        signature.setForeground(Color.white);
        this.add(signature);
        //
        background = new JLabel(new ImageIcon(new ImageIcon("images/backPhoto.png").
                getImage().getScaledInstance(720, 530, Image.SCALE_DEFAULT)));
        background.setBounds(0, 0, 720, 530);
        this.add(background);
        //this.setBackground(new Color(0,25,0));
    }

    
    public boolean getIsAppleMoving() {
        return isAppleMoving;
    }

    public boolean getIsThereWall() {
        return isThereWall;
    }

    public boolean getIsThereObstacle() {
        return isThereObstacle;
    }

    public EButton getClassicButton() {
        return classicButton;
    }

    public EButton getPeoplevsButton() {
        return peoplevsButton;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == classicButton){
            this.setVisible(false);
            this.isClassic = true;
        }
    
        if(e.getSource() == peoplevsButton){
            this.setVisible(false);
            this.isClassic = false;
        }
        if(e.getSource() == forApple){
            isAppleMoving = !isAppleMoving;
            forApple.setSelectionImage
        (isAppleMoving?new ImageIcon("images/leftHeadG.png"):new ImageIcon("images/rightHeadR.png"));
        }
        if(e.getSource() == forWall){
            isThereWall = !isThereWall;
            forWall.setSelectionImage
        (isThereWall?new ImageIcon("images/leftHeadG.png"):new ImageIcon("images/rightHeadR.png"));
        }
        if(e.getSource() == forObstacle){
            isThereObstacle = !isThereObstacle;
            forObstacle.setSelectionImage
        (isThereObstacle?new ImageIcon("images/leftHeadG.png"):new ImageIcon("images/rightHeadR.png"));
        }   
    }
}