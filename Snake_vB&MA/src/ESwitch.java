
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;


public class ESwitch extends JLabel implements MouseListener, ActionListener{
    
    private ActionListener actionListener;
    private ImageIcon baseImage, selectionImage;
    private int changeFast = 1000;
    
    private int id = 0;
    private int selectingIwidth, selectingIheight, borderValue, border1, border2;
    
    private boolean toRight = false;
    
    public JLabel selectionLabel;

    private Timer timer;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ESwitch(ActionListener actionListener){
        
        super();
        
        this.actionListener = actionListener;
        
        timer = new Timer(changeFast,this);
        
        addMouseListener(this);
    }    
    
    public ESwitch(ActionListener actionListener, ImageIcon image, int x, int y, int width, int height){
        
        super(new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        super.setBounds(x, y, width, height);
        
        this.actionListener = actionListener;
        this.baseImage = image;
        
        timer = new Timer(changeFast,this);
        
        addMouseListener(this);
    }    
    
    public ESwitch(ActionListener actionListener, ImageIcon image, int x, int y, int width, int height,
            int changeFast){
        
        super(new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        super.setBounds(x, y, width, height);
        
        this.actionListener = actionListener;
        this.baseImage = image;
        this.changeFast = changeFast;
        
        
        timer = new Timer(changeFast,this);
        
        addMouseListener(this);
    }
    
    public void setSelectionImage(ImageIcon selectionImage, int width, int heihgt, int borderValue, int y){
        this.selectionImage = selectionImage;
        this.selectingIwidth = width;
        this.selectingIheight = heihgt;
        this.borderValue = borderValue;
        
        selectionLabel = new JLabel(new ImageIcon(this.selectionImage.getImage().
                getScaledInstance(selectingIwidth, selectingIheight, Image.SCALE_DEFAULT)));
        selectionLabel.setBounds(0 + borderValue, y,
                selectingIwidth, selectingIheight);
        border1 = borderValue;
        border2 = this.getWidth() - borderValue - selectingIwidth;
        this.add(selectionLabel);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        actionListener.actionPerformed(new ActionEvent(this, id, "Changed"));
        toRight = ! toRight;
        timer.start();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e){
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public ImageIcon getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(ImageIcon baseImage) {
        this.baseImage = baseImage;
    }

    public int getChangeFast() {
        return changeFast;
    }

    public void setChangeFast(int changeFast) {
        this.changeFast = changeFast;
    }
    
    public ImageIcon getSelectionImage() {
        return selectionImage;
    }

    public void setSelectionImage(ImageIcon selectionImage) {
        this.selectionImage = selectionImage;
        selectionLabel.setIcon(new ImageIcon(this.selectionImage.getImage().
                getScaledInstance(selectingIwidth, selectingIheight, Image.SCALE_DEFAULT)));
        repaint();
    }

    public JLabel getSelectionLabel() {
        return selectionLabel;
    }

    public void setSelectionLabel(JLabel selectionLabel) {
        this.selectionLabel = selectionLabel;
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

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == timer){
            if(selectionLabel.getX() < border2 && toRight){
                selectionLabel.setLocation(selectionLabel.getX() + 2, selectionLabel.getY());
            }
            if(selectionLabel.getX() > border1 && !toRight){
                selectionLabel.setLocation(selectionLabel.getX() - 2, selectionLabel.getY());
            }
            if(selectionLabel.getX() == border2 || selectionLabel.getX() == border1){
                timer.stop();
            }
        }
    }
}