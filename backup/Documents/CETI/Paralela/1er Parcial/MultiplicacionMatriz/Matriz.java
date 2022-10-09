import java.util.Random;
import java.lang.Thread;

/**
 * Matriz
 */
public class Matriz {

  public static void main(String[] args) {
    // DECLARACION DE VARIABLES
    int m[][] = new int[3][3];
    Hilos hilo1 = new Hilos();
    Hilos hilo2 = new Hilos();
    Boolean h = false;
    Random random = new Random();
    int rellenado = 0;
    // RELLENADO DE MATRIZ
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        rellenado = random.nextInt(10);
        m[i][j] = rellenado;
        System.out.print(m[i][j] + " ");
      }
      System.out.println(" ");
    }
    // APLICACION DE HILOS
    hilo1.start();
    hilo2.start();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (!h) {
          try {
            hilo2.sleep(150);
            hilo1.setValor(m[i][j]);
            hilo1.sleep(50);
            m[i][j] = hilo1.getValor();
            h = true;
          } catch (InterruptedException e) {
            // TODO: handle exception
          }
        } else {
          try {
            hilo1.sleep(150);
            hilo2.setValor(m[i][j]);
            hilo2.sleep(50);
            m[i][j] = hilo2.getValor();
            h = false;
          } catch (InterruptedException e) {
            // TODO: handle exception
          }
        }
      }
    }
    hilo1.stop();
    hilo2.stop();
    System.out.println(" ");
    // IMPRESION DE MATRIZ
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print(m[i][j] + " ");
      }
      System.out.println(" ");
    }
  }
}

class Hilos extends Thread {
  int valor;

  Hilos() {
    valor = 0;
  }

  public void setValor(int v) {
    valor = v;
  }

  public int getValor() {
    return valor;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    for (;;) {
      try {
        valor = valor * 2;
        sleep(50);
      } catch (InterruptedException e) {
        // : handle exception
      }
    }
  }
}
