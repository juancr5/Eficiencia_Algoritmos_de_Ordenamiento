package algoritmos_de_ordenamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AlgoritmosDeOrdenamiento {

    public static void main(String[] args) {
        try {
            //Vector con todos los numeros a ordenar
            int numeros[] = leeFichero("Numeros.txt"); //Archivo con la cantidad de nuemros a evaluar

            Long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
            Bubble_Sort(numeros);
            //Seleccion_Sort(numeros);
            //Insertion_Sort(numeros);
            //Heap_Sort(numeros);
            //Quick_Sort(numeros); //Mas Eficiente
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
            System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); //Mostramos en pantalla el tiempo de ejecución en milisegundos
            
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    //Funcion Para Leer El Fichero y retornar un vector
    public static int[] leeFichero(String Nombre) throws IOException {

// Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        File archivo = new File(Nombre);
        FileReader fr = new FileReader(archivo);
        FileReader fraux = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        BufferedReader braux = new BufferedReader(fraux);

        //Tamaño del vector en lineas de texto
        String linea;
        String Aux;
        int numerolineas = 0;

        while ((Aux = braux.readLine()) != null) {
            numerolineas++;
        }
        
        // Creacion del Vecror
        int vector[] = new int[numerolineas];
        int posicionvector = 0;
        // Lectura del fichero
        while ((linea = br.readLine()) != null) {
            vector[posicionvector] = Integer.parseInt(linea);
            posicionvector++;
        }
        return vector;
    }

    //Metodo Burbuja
    public static void Bubble_Sort(int[] array) {
        int aux;
        boolean cambios;

        while (true) {
            cambios = false;
            for (int i = 1; i < array.length; i++) // mientras que i sea menor que el tamano del array
            {
                if (array[i] < array[i - 1]) //indice (i) ej posicion 2<1 o 1<0
                {
                    aux = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = aux;   // Estas 3 Lineas De Codigos Intercambian el valor 
                    // de esos numero en el array
                    cambios = true;
                }
            }
            if (cambios == false) {
                break;
            }
        }
    }

    //Ordenamiento Por Inserccion
    public static void Insertion_Sort(int[] array) {
        int tmp, j;
        for (int i = 1; i < array.length; i++) {
            tmp = array[i];
            j = i - 1;
            while ((j >= 0) && (array[j] > tmp)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    //Ordenamiento Por Selección
    public static void Seleccion_Sort(int[] array) {
        int menor, pos, tmp;
        for (int i = 0; i < array.length - 1; i++) { // tomamos como menor el primero
            menor = array[i]; // de los elementos que quedan por ordenar
            pos = i; // y guardamos su posición

            for (int j = i + 1; j < array.length; j++) { // buscamos en el resto
                if (array[j] < menor) { // del array algún elemento
                    menor = array[j]; // menor que el actual
                    pos = j;
                }
            }
            //Intercambiar
            tmp = array[i];
            array[i] = menor;
            array[pos] = tmp;

        }
    }

    //Ordenamiento Quicksort con Sobrecarga
    public static void Quick_Sort(int[] array) {
        int n = (array.length - 1);
        Quick_Sort(array, 0, n);
    }
    public static void Quick_Sort(int[] array, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }
        int pivote = array[inicio];
        int izq = inicio + 1;
        int der = fin;
        while (izq <= der) {
            while (izq <= fin && array[izq] < pivote) {
                izq++;
            }
            while (der > inicio && array[der] >= pivote) {
                der--;
            }
            if (izq < der) {
                int tmp = array[izq];
                array[izq] = array[der];
                array[der] = tmp;
            }
        }
        if (der > inicio) {
            int tmp = array[inicio];
            array[inicio] = array[der];
            array[der] = tmp;
        }
        Quick_Sort(array, inicio, der - 1);
        Quick_Sort(array, der + 1, fin);
    }

    //Ordenamiento Heap_Sort
    public static void Heap_Sort(int[] array) {
        int size = array.length;
        int i, temp;
        for (i = size / 2 - 1; i >= 0; i--) {
            Heapify(array, size, i);
        }
        for (i = size - 1; i >= 0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            Heapify(array, i, 0);
        }
    }
    public static void Heapify(int arr[], int size, int i) {
        int temp;
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            Heapify(arr, size, largest);
        }
    }

    public static void MostrarVector(int[] vector) // Función Para Mostrar El Vector Horizontalmente
    {
        for (int i = 0; i < vector.length; i++) {
            System.out.println(vector[i]);
        }
        System.out.println(" ");
    }
}
