/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;


public class Draggable extends JComponent {
    private final Point originalLocation;
    private PartidaEnJuego context;
    private List<JPanel> centerZone;
    private boolean isDragging = false;
    private List<JPanel> handZone;
    private Point pointPressed;
    private boolean action = false;
    private final JComponent draggable;

    public Draggable(final JComponent component, final int x, final int y,PartidaEnJuego context, List<JPanel> centerZone, List<JPanel> handZone) {
        this.context = context;
        this.centerZone = centerZone;
        this.handZone = handZone;
        draggable = component;
        originalLocation = new Point(x,y);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLocation(x, y);
        setSize(component.getPreferredSize());
        setLayout(new BorderLayout());
//        setOpaque(false);
        add(component);
        MouseInputAdapter mouseAdapter = new MouseHandler();
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }

    @Override
    public void setBorder(final Border border) {
        super.setBorder(border);
        if (border != null) {
            Dimension size = draggable.getPreferredSize();
            Insets insets = border.getBorderInsets(this);
            size.width += (insets.left + insets.right + 5);
            size.height += (insets.top + insets.bottom);
            setSize(size);
        }
    }

    public void setAction(boolean action){
        this.action = action;
    }

    private class MouseHandler extends MouseInputAdapter {
        @Override
        public void mouseDragged(final MouseEvent e) {
            if (action && !isDragging) {
                isDragging = true;
                SwingUtilities.invokeLater(() -> {
                    Point pointDragged = e.getPoint();
                    Point loc = getLocation();
                    loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
                    setLocation(loc);
                    isDragging = false;
                    repaint();
                });
            }
        }
        @Override
        public void mousePressed(final MouseEvent e) {
            pointPressed = e.getPoint();
//            disableRepaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//            enableRepaint();
            Point pointReleased = SwingUtilities.convertPoint(Draggable.this, e.getPoint(), getParent());
            setLocation(originalLocation);
            int zonasCentro = 0;
            int zonasMano = 0;
            if (action){
                for (JPanel center : centerZone){
                    if (center.getBounds().contains(pointReleased)){
                        for (JPanel card : handZone){
                            Point realPos = originalLocation.getLocation();
                            realPos.translate(60,0);
                            if (card.getBounds().contains(realPos)){
//                                context.getController().playTurn(zonasMano,zonasCentro);
                                try {
                                    context.getController().jugarTurno(zonasMano, zonasCentro);
                                } catch (RemoteException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            zonasMano++;
                        }
                    }
                    zonasCentro++;
                }
            }
        }
    }
}