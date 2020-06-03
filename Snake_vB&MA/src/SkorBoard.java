
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SkorBoard extends JPanel implements ActionListener{
    
    JFrame window;
    
    ArrayList<JLabel> skorLabels;
    static JLabel recordLabel;
    
    EButton exitButton, playAgainButton, homeButton;
    
    static int record = 0; 
    
    public SkorBoard(int width,boolean isClassic, ActionListener actionListener){
        super();
        this.setLayout(null);
        this.setBackground(new Color(0,170,0));
        
        JLabel ppLabel1 = new JLabel(new ImageIcon(new ImageIcon("images/rightHeadG.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        ppLabel1.setBounds(isClassic?(width/2)-25:((width/2 - 50)/2)+55, 15, 20, 20);
        
        if(isClassic){
            JLabel cup = new JLabel(new ImageIcon(new ImageIcon("images/cup.png").getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT)));
            cup.setBounds((width/2)+5, 10, 25, 30);
            add(cup);
            recordLabel = new JLabel(Integer.toString(record));
            recordLabel.setBounds((width/2)+40, 0, 50, 50);
            recordLabel.setFont(new Font("Arial", Font.BOLD, 18));
            recordLabel.setForeground(Color.WHITE);
            add(recordLabel);
        }
        
        skorLabels = new ArrayList<JLabel>();
        
        skorLabels.add(new JLabel("0"));
        skorLabels.get(0).setBounds(isClassic?(width/2)-50:(width/2 - 50)/2+30, 0, 50, 50);
        skorLabels.get(0).setFont(new Font("Arial", Font.BOLD, 18));
        skorLabels.get(0).setForeground(Color.WHITE);
        
        if(!isClassic){
            JLabel ppLabel2 = new JLabel(new ImageIcon(new ImageIcon("images/leftHeadR.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            ppLabel2.setBounds((width/2 +(width/2 - 50)/2)-30, 15, 20, 20);
            
            skorLabels.add(new JLabel("0"));
            skorLabels.get(1).setBounds(width/2 +(width/2 - 50)/2, 0, 50, 50);
            skorLabels.get(1).setFont(new Font("Arial", Font.BOLD, 18));
            skorLabels.get(1).setForeground(Color.WHITE);
            add(ppLabel2);
            add(skorLabels.get(1));
        }
        
        exitButton = new EButton(this, new ImageIcon("images/exit.png"), width - 50, 10, 30, 30, 1.2);
        playAgainButton = new EButton(actionListener, new ImageIcon("images/refresh.png"), 20, 10, 30, 30, 1.2);
        playAgainButton.setId(666);
        homeButton = new EButton(actionListener, new ImageIcon("images/home.png"), 60, 10, 30, 30, 1.2);
        homeButton.setId(111);
        
        add(exitButton);
        add(playAgainButton);
        add(homeButton);
        add(ppLabel1);
        add(skorLabels.get(0));
    }

    public static int getRecord() {
        return record;
    }

    public static void setRecord(int recor) {
        SkorBoard.record = recor;
        recordLabel.setText(Integer.toString(recor));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}