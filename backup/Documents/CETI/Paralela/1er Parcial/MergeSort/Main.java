import java.util.Random;
import java.util.Scanner;

// JONATHAN GUILLERMO DIAZ MAGALLANES

/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    System.out.println("Ingrese el tamaño del array: ");
    int tam = sc.nextInt();
    int[] arr = new int[tam];
    // Rellenamos el arreglod e valores aleatorios
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(98 + 1);
      System.out.print(arr[i] + " ");
    }
    // Imprimimos el arreglo
    System.out.println(" ");
    mergeSort(arr);
    // Imprimimos el arreglo ordenado
    for (int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + " ");
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
