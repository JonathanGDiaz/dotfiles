
//JONATHAN GUILLERMO DIAZ MAGALLANES
import java.lang.Thread;

public class Main {
  public static void main(String[] args) {
    // DECLARACION DE VARIABLES
    String[] letras = { "a", "b", "c", "d", "e" };
    int[] num = { 1, 2, 3, 4, 5 };
    Sincronizacion s = new Sincronizacion();
    // DECLARACION DEL PRIMER HILO DE LETRAS
    Thread h1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for (int i = 0; i < 5; i++) {
            s.setLetras(letras[i]);
            s.impLetra();
          }
        } catch (Exception e) {
        }
      }
    });
    // DECLARACION DEL SEGUNDO HILO DE NUMEROS
    Thread h2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for (int i = 0; i < 5; i++) {
            s.setNumeros(num[i]);
            s.impNum();
          }
        } catch (Exception e) {
        }
      }
    });

    h1.start();
    h2.start();
    try {
      Thread.sleep(400);
    } catch (Exception InterruptedExceptionnter) {
    }
    h1.stop();
    h2.stop();
  }
}

class Sincronizacion {
  String letras = "";
  int numeros = 0;

  public void setLetras(String l) {
    letras = l;
  }

  public void setNumeros(int n) {
    numeros = n;
  }

  // METODOS DE IMPRESION
  public void impLetra() throws InterruptedException {
    synchronized (this) {
      System.out.println("El " + Thread.currentThread().getName() + " dice " + letras);
      notify();
      wait();
    }
  }

  public void impNum() throws InterruptedException {
    synchronized (this) {
      Thread.sleep(50);
      System.out.println("El " + Thread.currentThread().getName() + " dice " + String.valueOf(numeros));
      notify();
      wait();
    }
  }
}
