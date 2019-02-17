
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ionut Cicio
 */
public class CustomButton extends BasicButtonUI{
    int animationIndex = 0, numberOfFrames = 5, target = 0;
    int decorationIsets = 8, circleDiameter = 8, lineWeight = circleDiameter / 3;

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D)g;
        
        RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        
        //Drawing background
        if (animationIndex != target){
            if (animationIndex < target){
                animationIndex++;
            } else if (animationIndex > target){
                animationIndex--;
            }
        }
        
        if (target == 0 && animationIndex == target){
            //g2.setColor(c.getBackground());
            g2.setPaint(new GradientPaint(
                c.getWidth() / 2,
                0,
                c.getBackground(),
                c.getWidth() / 2,
                c.getHeight(),
                new Color(70, 70, 70)
            ));
        } else if (c.hasFocus()/*&& animationIndex == target && target != 0*/) {
            g2.setColor(new Color(
                    animationIndex * (200 / numberOfFrames),
                    animationIndex * (200 / numberOfFrames),
                    animationIndex * (200 / numberOfFrames)));
        }
        
        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);
        
        //Decorations
        g2.setColor(c.getForeground());
        
        g2.fillOval( //Left-Up corner
                decorationIsets - circleDiameter / 2,
                decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter); 
        
        g2.fillOval( //Left-Down corner
                decorationIsets - circleDiameter / 2,
                c.getHeight() - decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter);
        
        g2.fillOval( //Right-Up corner
                c.getWidth() - decorationIsets - circleDiameter / 2,
                decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter);
        
        g2.fillOval( //Right-Down corner
                c.getWidth() - decorationIsets - circleDiameter / 2,
                c.getHeight() - decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter);
    
        g2.fillRect( //Up line
                decorationIsets,
                decorationIsets - lineWeight / 2,
                c.getWidth() - decorationIsets * 2,
                lineWeight);
        
        g2.fillRect( //Down line
                decorationIsets,
                c.getHeight() - decorationIsets - lineWeight / 2,
                c.getWidth() - decorationIsets * 2,
                lineWeight);
        
        g2.fillRect( //Right line
                decorationIsets - lineWeight / 2,
                decorationIsets,
                lineWeight,
                c.getHeight() - decorationIsets * 2);
        
        g2.fillRect( //Left line
                c.getWidth() - decorationIsets - lineWeight / 2,
                decorationIsets,
                lineWeight,
                c.getHeight() - decorationIsets * 2);
        
        super.paint(g, c);
    }
}
