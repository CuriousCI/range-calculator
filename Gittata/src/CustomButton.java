
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
    public void paint(Graphics graphics, JComponent component) {
        Graphics2D graphics2 = (Graphics2D)graphics;
        
        RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2.setRenderingHints(qualityHints);
        
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
            graphics2.setPaint(new GradientPaint(
                component.getWidth() / 2,
                0,
                component.getBackground(),
                component.getWidth() / 2,
                component.getHeight(),
                new Color(70, 70, 70)
            ));
        } else if (component.hasFocus()/*&& animationIndex == target && target != 0*/) {
            graphics2.setColor(new Color(
                    animationIndex * (200 / numberOfFrames),
                    animationIndex * (200 / numberOfFrames),
                    animationIndex * (200 / numberOfFrames)));
        }
        
        graphics2.fillRoundRect(0, 0, component.getWidth(), component.getHeight(), 8, 8);
        
        //Decorations
        graphics2.setColor(component.getForeground());
        
        graphics2.fillOval( //Left-Up corner
                decorationIsets - circleDiameter / 2,
                decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter); 
        
        graphics2.fillOval( //Left-Down corner
                decorationIsets - circleDiameter / 2,
                component.getHeight() - decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter);
        
        graphics2.fillOval( //Right-Up corner
                component.getWidth() - decorationIsets - circleDiameter / 2,
                decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter);
        
        graphics2.fillOval( //Right-Down corner
                component.getWidth() - decorationIsets - circleDiameter / 2,
                component.getHeight() - decorationIsets - circleDiameter / 2,
                circleDiameter,
                circleDiameter);
    
        graphics2.fillRect( //Up line
                decorationIsets,
                decorationIsets - lineWeight / 2,
                component.getWidth() - decorationIsets * 2,
                lineWeight);
        
        graphics2.fillRect( //Down line
                decorationIsets,
                component.getHeight() - decorationIsets - lineWeight / 2,
                component.getWidth() - decorationIsets * 2,
                lineWeight);
        
        graphics2.fillRect( //Right line
                decorationIsets - lineWeight / 2,
                decorationIsets,
                lineWeight,
                component.getHeight() - decorationIsets * 2);
        
        graphics2.fillRect( //Left line
                component.getWidth() - decorationIsets - lineWeight / 2,
                decorationIsets,
                lineWeight,
                component.getHeight() - decorationIsets * 2);
        
        super.paint(graphics, component);
    }
}
