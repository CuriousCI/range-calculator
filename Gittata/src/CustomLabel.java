
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicLabelUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ionut Cicio
 */
public class CustomLabel extends BasicLabelUI{
    int decorationIsets = 8, lineWeight = 2;
    
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
        
        //Background
        
        g2.setPaint(new GradientPaint(
                c.getWidth() / 2,
                0,
                c.getBackground(),
                c.getWidth() / 2,
                c.getHeight(),
                new Color(70, 70, 70)
        ));
        
        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);
        
        g2.setColor(Color.BLACK);
        g2.fillRect(decorationIsets, decorationIsets, c.getWidth() - decorationIsets * 2, c.getHeight() - decorationIsets * 2);
        
        //Decorations
        g2.setColor(c.getForeground());
        
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
