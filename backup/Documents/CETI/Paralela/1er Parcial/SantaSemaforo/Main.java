// JONATHAN GUILLERMO DIAZ MAGALLANES
/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    int duendes = 0, renos = 0;
    boolean comiendo = false;
    Productor productorDuendes = new Productor(duendes, "duendes", comiendo);
    Productor productorRenos = new Productor(renos, "renos", comiendo);
    Consumidor santa = new Consumidor(productorDuendes, productorRenos);
    productorRenos.start();
    productorDuendes.start();
    santa.start();
  }
}

class Productor extends Thread {
  private int variable;
  private String nombre;
  private boolean comiendo;

  public Productor(int variable, String nombre, boolean comiendo) {
    this.variable = variable;
    this.nombre = nombre;
    this.comiendo = comiendo;
  }

  public void setComiendo(boolean comiendo) {
    this.comiendo = comiendo;
  }

  public boolean getComiendo() {
    return this.comiendo;
  }

  public int getVariable() {
    return variable;
  }

  public void setVariable(int variable) {
    this.variable = variable;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public void run() {
    for (;;) {
      if (!comiendo) {
        if (variable < 9) {
          variable++;
          System.out.println("El productor de " + nombre + " añadio " + variable);
          try {
            if (nombre == "duendes") {
              Thread.sleep(1000);
            } else {
              Thread.sleep(500);
            }
          } catch (Exception e) {
          }
        }
      } else {
        try {
          this.wait();
        } catch (Exception e) {
        }
      }
      if (variable == 9) {
        try {
          this.wait();
        } catch (Exception e) {
        }
      }
    }
  }
}

class Consumidor extends Thread {
  Productor productorDuendes;
  Productor productorRenos;

  public Consumidor(Productor productorDuendes, Productor productorRenos) {
    this.productorDuendes = productorDuendes;
    this.productorRenos = productorRenos;
  }

  @Override
  public void run() {
    for (;;) {
      if (productorRenos.getVariable() == 9) {
        for (int i = 0; i < 9; i++) {
          comer(productorRenos);
          if (productorDuendes.getVariable() == 9)
            break;
        }
      } else {
        productorRenos.setComiendo(false);
      }
      if (productorDuendes.getVariable() == 9)
        for (int i = 0; i < 9; i++) {
          comer(productorDuendes);
        }
      productorDuendes.setComiendo(false);
    }
  }

  private void comer(Productor productor) {
    productor.setComiendo(true);
    int temp = productor.getVariable() - 1;
    productor.setVariable(temp);
    System.out.println("Santa recogio a un " + productor.getNombre());
    try {
      Thread.sleep(500);
    } catch (Exception e) {
    }
  }
}
