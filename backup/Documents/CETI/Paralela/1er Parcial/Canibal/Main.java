import java.util.Random;

// JONATHAN GUILLERMO DIAZ MAGALLANES
/**
 * Main
 */
public class Main implements Runnable {
  // VARIABLES DE IDENTIFICACION
  private boolean esConsumidor;
  private int id;

  // VARIABLES DE CONTROL
  private static int comida = 0;
  private static Object lock = new Object();

  public Main(Boolean esConsumidor, int id) {
    this.esConsumidor = esConsumidor;
    this.id = id;
  }

  public static void main(String[] args) {
    Random random = new Random();
    int numHilos = random.nextInt(8) + 5;
    Thread[] arrHilos = new Thread[numHilos];
    // START DE LOS HILOS
    for (int i = 0; i < arrHilos.length; i++) {
      Runnable runnable = null;
      if (i != 0) {
        runnable = new Main(true, i);
      } else {
        runnable = new Main(false, i);
      }
      arrHilos[i] = new Thread(runnable);
      arrHilos[i].start();
    }
    // JOIN DE LOS HILOS
    for (int i = 0; i < arrHilos.length; i++) {
      try {
        arrHilos[i].join();
      } catch (Exception e) {
      }
    }
  }

  // METODO RUN PARA HILOS
  @Override
  public void run() {
    for (;;) {
      if (esConsumidor) {
        consumir();
      } else {
        producir();
      }
    }
  }

  // METODO DE PRODUCCION
  private void producir() {
    synchronized (lock) {
      if (comida == 0) {
        for (int i = 0; i < 8; i++) {
          comida++;
          System.out.println("El productor " + id + " puso " + comida);
          try {
            Thread.sleep(500);
          } catch (Exception e) {
          }
        }
      }
      try {
        lock.wait();
      } catch (Exception e) {
      }
    }
  }

  // METODO DE CONSUMCION
  private void consumir() {
    synchronized (lock) {
      if (comida > 0) {
        comida--;
        System.out.println("El consumidor " + id + " dejo " + comida);
        try {
          Thread.sleep(500);
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
