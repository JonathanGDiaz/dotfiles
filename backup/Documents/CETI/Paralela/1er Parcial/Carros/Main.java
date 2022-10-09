import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// JONATHAN GUILLERMO DIAZ MAGALLANES
/**
 * Main
 */
public class Main implements Runnable {
  // VARIABLES DE IDENTIFICACION
  private boolean esDerecho, esProductor;
  private int id;

  // VARIABLES DE CONTROL
  private static int carrosDerecha = 0, carrosIzquierda = 0;
  private static Object lock = new Object();
  public static boolean s = false;

  public Main(boolean esDerecho, int id, boolean esProductor) {
    this.esProductor = esProductor;
    this.esDerecho = esDerecho;
    this.id = id;
  }

  public static void main(String[] args) {
    Thread[] arrHilos = new Thread[4];
    // START DE LOS HILOS
    for (int i = 0; i < arrHilos.length; i++) {
      Runnable runnable = null;
      if (i < 2) {
        // LOS HILOS SON PRODUCTORES
        if (i == 0) {
          // ES PRODUCTOR DERECHO
          runnable = new Main(true, i, true);
        } else if (i == 1) {
          // ES PRODUCTOR IZQUIERDO
          runnable = new Main(false, i, true);
        }
      }
      // LOS HILOS SON CONSUMIDORES
      else if (i == 2) {
        // EL HILO ES DERECHO
        runnable = new Main(true, i, false);
      } else if (i == 3) {
        // EL HILO ES IZQUIERDO
        runnable = new Main(false, i, false);
      }
      arrHilos[i] = new Thread(runnable);
      arrHilos[i].start();
    }
    new MyFrame();
    // JOIN DE LOS HILOS
    for (int i = 0; i < arrHilos.length; i++) {
      try {
        arrHilos[i].join();
      } catch (Exception e) {
      }
    }
  }

  @Override
  public void run() {
    for (;;) {
      if (esProductor) {
        if (esDerecho) {
          producirDerecha();
        } else {
          producirIzquierda();
        }
      } else if (esDerecho) {
        derecha();
      } else {
        izquierda();
      }
    }
  }

  private void producirDerecha() {
    synchronized (lock) {
      if (carrosDerecha == 0) {
        for (int i = 0; i < 3; i++) {
          carrosDerecha++;
          System.out.println("El productor derecho " + id + " añadio " + carrosDerecha + " carros");
          try {
            Thread.sleep(500);
          } catch (Exception e) {
          }
        }
        try {
          lock.wait();
        } catch (Exception e) {
        }
      }
    }
  }

  private void derecha() {
    synchronized (lock) {
      if (carrosDerecha > 0) {
        for (int i = 0; i < 3; i++) {
          carrosDerecha--;
          System.out.println("El consumidor derecho " + id + " avanzo un carro");
          // Main.s = true;
          try {
            Thread.sleep(5500);
          } catch (Exception e) {
          }
        }
        try {
          Thread.sleep(500);
          // Main.s = false;
          lock.notifyAll();
          lock.wait();
        } catch (Exception e) {
        }
      } else {
        lock.notifyAll();
        try {
          lock.wait();
        } catch (Exception e) {
        }
      }
    }
  }

  private void producirIzquierda() {
    synchronized (lock) {
      if (carrosIzquierda == 0) {
        for (int i = 0; i < 3; i++) {
          carrosIzquierda++;
          System.out.println("El productor izquierdo " + id + " añadio " + carrosIzquierda + " carros");
          try {
            Thread.sleep(500);
          } catch (Exception e) {
          }
        }
        try {
          lock.wait();
        } catch (Exception e) {
        }
      }
    }
  }

  private void izquierda() {
    synchronized (lock) {
      if (carrosIzquierda > 0) {
        for (int i = 0; i < 3; i++) {
          carrosIzquierda--;
          System.out.println("El consumidor izquierdo " + id + " avanzo un carro");
          // Main.s = false;
          try {
            Thread.sleep(5500);
          } catch (Exception e) {
          }
        }
        try {
          Thread.sleep(500);
          // Main.s = true;
          lock.notifyAll();
          lock.wait();
        } catch (Exception e) {
        }
      } else {
        lock.notifyAll();
        try {
          lock.wait();
        } catch (Exception e) {
        }
      }
    }

  }
}

// class MyFrame extends JFrame {
// MyPanel panel;

// MyFrame() {
// panel = new MyPanel();
// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// this.add(panel);
// this.pack();
// this.setLocationRelativeTo(null);
// this.setVisible(true);
// }
// }

// class MyPanel extends JPanel implements ActionListener {
// final int PANEL_WIDTH = 612;
// final int PANEL_HEIGHT = 402;
// Image carroDerecha;
// Image carroIzquierda;
// Image fondo;
// Timer timer;
// int xVel = 1;
// int xDerecha = 0, yDerecha = 70;
// int xIzquierda = 487, yIzquierda = 265;
// int i = 0;

// MyPanel() {
// this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
// carroDerecha = new ImageIcon("carroDerecha.png").getImage();
// carroIzquierda = new ImageIcon("carroIzquierda.png").getImage();
// fondo = new ImageIcon("road.jpg").getImage();
// timer = new Timer(10, this);
// timer.start();
// }

// @Override
// public void paint(Graphics g) {
// // super.paint(g);
// Graphics2D g2d = (Graphics2D) g;
// g2d.drawImage(fondo, 0, 0, null);
// g2d.drawImage(carroDerecha, xDerecha, yDerecha, 125, 75, null);
// g2d.drawImage(carroIzquierda, xIzquierda, yIzquierda, 125, 75, null);
// }

// @Override
// public void actionPerformed(ActionEvent arg0) {
// if (Main.s) {
// if (i < 3) {
// xDerecha = xDerecha + xVel;
// if (xDerecha >= PANEL_WIDTH) {
// xDerecha = 0;
// i++;
// }
// } else {
// Main.s = false;
// i = 0;
// }
// } else {
// if (i < 3) {
// xIzquierda = xIzquierda - xVel;
// if (xIzquierda == -125) {
// xIzquierda = 487;
// i++;
// }
// } else {
// Main.s = true;
// i = 0;
// }
// // xIzquierda = xIzquierda - xVel;
// // if (xIzquierda == -125) {
// // xIzquierda = 487;
// // }
// }
// repaint();
// }
// }
