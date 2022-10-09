
//JONATHAN GUILLERMO DIAZ MAGALLANES
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Main
 */
public class Main extends JFrame implements ActionListener {
  final int PANEL_WIDTH = 730;
  final int PANEL_HEIGHT = 750;
  static JTextField texto;
  static JButton crear, ordenar;
  static JTextArea label;
  static JLabel labelTiempo;
  static int[] arr;
  static double tiempo = 0;
  static boolean llave = false;

  Main() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.setLayout(null);
    this.setTitle("Algoritmo MergeSort");
    this.setLocationRelativeTo(null);
    Border border = BorderFactory.createLineBorder(Color.black, 3);
    // VALORES PARA EL TEXTFIELD DEL TAMAÑO DEL ARREGLO
    texto = new JTextField();
    texto.setBounds(10, 25, 150, 25);
    this.add(texto);
    // VALORES DEL BOTON PARA CREAR EL ARREGLO
    crear = new JButton("Crear");
    crear.setBounds(170, 25, 75, 25);
    crear.addActionListener(this);
    this.add(crear);
    // VALORES DEL BOTON PARA ORDENAR EL ARREGLO
    ordenar = new JButton("Ordenar");
    ordenar.setBounds(255, 25, 100, 25);
    ordenar.addActionListener(this);
    this.add(ordenar);
    // VALORES DE LA ETIQUETA PARA EL ARREGLO
    label = new JTextArea();
    label.setWrapStyleWord(true);
    label.setLineWrap(true);
    label.setBounds(10, 60, 700, 600);
    label.setBorder(border);
    this.add(label);
    // VALORES DE LA ETIQUETA PARA EL TIEMPO
    labelTiempo = new JLabel();
    labelTiempo.setBounds(300, 680, 175, 25);
    this.add(labelTiempo);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == crear)
      crearArreglo();
    else if (e.getSource() == ordenar) {
      double inicio = System.nanoTime();
      ordenarArreglo();
      double fin = System.nanoTime();
      tiempo = (double) (fin - inicio) / Math.pow(10, 6);
      System.out.println(tiempo + " milisegundos");
      labelTiempo.setText(Double.toString(tiempo) + " milisegundos");
      imprimirArreglo();
    }
  }

  // --------------------------Metodos de botones-----------------------
  public void crearArreglo() {
    int tam = Integer.parseInt(texto.getText());
    Random random = new Random();
    arr = new int[tam];
    // Rellenamos el arreglod e valores aleatorios
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(98 + 1);
    }
    imprimirArreglo();
  }

  public void ordenarArreglo() {
    mergeSort(arr);
  }

  public void imprimirArreglo() {
    String temp = "";
    label.setText(temp);
    for (int i = 0; i < arr.length; i++) {
      temp = label.getText();
      temp = temp + " " + Integer.toString(arr[i]);
      label.setText(temp);
    }
  }

  // --------------------------Metodos de acomodo-----------------------
  private static void mergeSort(int[] arr) {
    int tam = arr.length;
    // Si solo queda un elemento se termina
    if (tam <= 1)
      return;
    // Obtenemos valor medio para cortar el array
    int mid = tam / 2;
    // Declaramos dos sub arreglos
    int[] arrIzquierda = new int[mid];
    int[] arrDerecha = new int[tam - mid];
    // Declaramos los indices de los arreglos
    int indexDerecha = 0, indexIzquierda = 0;
    // Añadimos los valores a los nuevos arreglos
    for (; indexIzquierda < tam; indexIzquierda++) {
      if (indexIzquierda < mid)
        arrIzquierda[indexIzquierda] = arr[indexIzquierda];
      else {
        arrDerecha[indexDerecha] = arr[indexIzquierda];
        indexDerecha++;
      }
    }
    // Empezamos la recursion
    mergeSort(arrIzquierda);
    mergeSort(arrDerecha);
    // Volvemos a juntar los arreglos
    merge(arrIzquierda, arrDerecha, arr);
  }

  private static void merge(int[] arrIzquierda, int[] arrDerecha, int[] arr) {
    // Obtenemos tamaños de los arreglos
    int tamIzquierda = arr.length / 2;
    int tamDerecha = arr.length - tamIzquierda;
    // Declaramos los indices
    int i = 0, indexIzquierda = 0, indexDerecha = 0;
    // Empezamos con la asignacion de valores
    while (indexIzquierda < tamIzquierda && indexDerecha < tamDerecha) {
      // Comparamos los valores
      if (arrIzquierda[indexIzquierda] < arrDerecha[indexDerecha]) {
        arr[i] = arrIzquierda[indexIzquierda];
        i++;
        indexIzquierda++;
      } else {
        arr[i] = arrDerecha[indexDerecha];
        i++;
        indexDerecha++;
      }
    }
    // Añadimos los valores que pueden llegar a sobrar
    while (indexIzquierda < tamIzquierda) {
      arr[i] = arrIzquierda[indexIzquierda];
      i++;
      indexIzquierda++;
    }
    while (indexDerecha < tamDerecha) {
      arr[i] = arrDerecha[indexDerecha];
      i++;
      indexDerecha++;
    }
  }
}
