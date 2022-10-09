import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyPanel extends JPanel implements ActionListener {
  final int PANEL_WIDTH = 612;
  final int PANEL_HEIGHT = 402;
  Image carroDerecha;
  Image carroIzquierda;
  Image fondo;
  Timer timer;
  int xVel = 1;
  int xDerecha = 0, yDerecha = 70;
  int xIzquierda = 487, yIzquierda = 265;
  int i = 0;

  MyPanel() {
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    carroDerecha = new ImageIcon("carroDerecha.png").getImage();
    carroIzquierda = new ImageIcon("carroIzquierda.png").getImage();
    fondo = new ImageIcon("road.jpg").getImage();
    timer = new Timer(10, this);
    timer.start();
  }

  @Override
  public void paint(Graphics g) {
    // super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(fondo, 0, 0, null);
    g2d.drawImage(carroDerecha, xDerecha, yDerecha, 125, 75, null);
    g2d.drawImage(carroIzquierda, xIzquierda, yIzquierda, 125, 75, null);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (Main.s) {
      if (i < 3) {
        xDerecha = xDerecha + xVel;
        if (xDerecha >= PANEL_WIDTH) {
          xDerecha = 0;
          i++;
        }
      } else {
        Main.s = false;
        i = 0;
      }
    } else {
      if (i < 3) {
        xIzquierda = xIzquierda - xVel;
        if (xIzquierda == -125) {
          xIzquierda = 487;
          i++;
        }
      } else {
        Main.s = true;
        i = 0;
      }
      // xIzquierda = xIzquierda - xVel;
      // if (xIzquierda == -125) {
      // xIzquierda = 487;
      // }
    }
    repaint();
  }
}
