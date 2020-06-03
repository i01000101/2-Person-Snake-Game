
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class EButton extends JLabel implements MouseListener{
    
    private ActionListener actionListener;
    private ImageIcon image;
    private double biggeringRate = 1;
    
    private int xRate, yRate, id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public EButton(ActionListener actionListener){
        
        super();
        
        this.actionListener = actionListener;
        
        addMouseListener(this);
    }    
    
    public EButton(ActionListener actionListener, ImageIcon image, int x, int y, int width, int height){
        
        super(new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        super.setBounds(x, y, width, height);
        
        this.actionListener = actionListener;
        this.image = image;
        
        xRate = (int) Math.round(width*(biggeringRate - 1)/2);
        yRate = (int) Math.round(height*(biggeringRate - 1)/2);
        
        addMouseListener(this);
    }    
    
    public EButton(ActionListener actionListener, ImageIcon image, int x, int y, int width, int height,
            double biggeringRate){
        
        super(new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        super.setBounds(x, y, width, height);
        
        this.actionListener = actionListener;
        this.image = image;
        this.biggeringRate = biggeringRate;
        
        xRate = (int) Math.round(width*(biggeringRate - 1)/2);
        yRate = (int) Math.round(height*(biggeringRate - 1)/2);
        
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        actionListener.actionPerformed(new ActionEvent(this, id, "Pressed"));
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e){
        super.setLocation(super.getX() - xRate, super.getY() - yRate);
        super.setSize((int) Math.round(super.getWidth()*biggeringRate),
                (int) Math.round(super.getHeight()*biggeringRate));
        super.setIcon(new ImageIcon(image.getImage().getScaledInstance(super.getWidth(), super.getHeight(),
                Image.SCALE_DEFAULT)));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent me) {
        super.setLocation(super.getX() + xRate, super.getY() + yRate);
        super.setSize((int) Math.round(super.getWidth()/biggeringRate),
                (int) Math.round(super.getHeight()/biggeringRate));
        super.setIcon(new ImageIcon(image.getImage().getScaledInstance(super.getWidth(), super.getHeight(),
                Image.SCALE_DEFAULT)));
}

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public double getBiggeringRate() {
        return biggeringRate;
    }

    public void setBiggeringRate(double biggeringRate) {
        this.biggeringRate = biggeringRate;
    }

    @Override
    public void setBounds(int i, int i1, int i2, int i3) {
        super.setBounds(i, i1, i2, i3);
    }

    @Override
    public void setSize(int i, int i1) {
        super.setSize(i, i1);
    }

    @Override
    public void setLocation(int i, int i1) {
        super.setLocation(i, i1);
    }
}