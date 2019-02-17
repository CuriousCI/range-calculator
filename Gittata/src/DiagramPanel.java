
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicPanelUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ionut Cicio
 */
public class DiagramPanel extends BasicPanelUI {
    
    RenderingHints qualityHints;
    int borderDistance = 30, tackSize = 3;
    float diagramHeight, diagramWidth, acceleration, speed, time;
    Vector<Integer> dotX;
    Vector<Integer> dotY;
    boolean isDrawable;

    public DiagramPanel() {
        qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        this.setValues(0, 0, 0, 0);
        this.isDrawable = false;
        
        dotX = new Vector<>();
        dotY = new Vector<>();
    }

    final public void setValues(float diagramHeight, float diagramWidth, float acceleration, float speed) {
        this.diagramHeight = diagramHeight;
        this.diagramWidth = diagramWidth;
        this.acceleration = acceleration;
        this.speed = speed;
    }

    @Override
    public void update(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(qualityHints);

        g2.setColor(c.getBackground());
        g2.fillRoundRect(8, 8, c.getWidth() - 16, c.getHeight() - 16, 8, 8);

        super.update(g, c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if (isDrawable) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHints(qualityHints);
            
            g2.setColor(new Color(0, 0, 0));
            for (time = 0; time < 10; time++) {
                int y = (int)(borderDistance + (int) ((0.5) * (acceleration) * (time * time)));
                int x = (int)(borderDistance + speed * time);
                
                dotX.add((int)time, x);
                dotY.add((int)time, y);
            }
            
            for (int i = 0; i < 9; i++){
                g2.drawLine(dotX.elementAt(i), dotY.elementAt(i), dotX.elementAt(i + 1), dotY.elementAt(i + 1));
            }
            
            g2.setColor(new Color(255, 255, 255));
            g2.fillRect(borderDistance, c.getHeight() - borderDistance, c.getWidth() - borderDistance * 2, borderDistance * 3 / 4);

            //Cartesian plane
            g2.setColor(new Color(0, 0, 0));
            g2.drawLine( //Y axis
                    borderDistance,
                    borderDistance,
                    borderDistance,
                    c.getHeight() - borderDistance);
            g2.drawLine( //X axis
                    borderDistance,
                    c.getHeight() - borderDistance,
                    c.getWidth() - borderDistance,
                    c.getHeight() - borderDistance);

            for (int i = borderDistance; i <= c.getHeight() - borderDistance; i += (c.getHeight() - borderDistance * 2) / diagramHeight) {
                g2.drawLine(borderDistance - tackSize, i, borderDistance + tackSize, i);
            }

            for (int i = borderDistance; i <= c.getWidth() - borderDistance; i += (c.getHeight() - borderDistance * 2) / diagramWidth) {
                g2.drawLine(i, c.getHeight() - borderDistance - tackSize, i, c.getHeight() - borderDistance + tackSize);
            }
        }
    }
}
