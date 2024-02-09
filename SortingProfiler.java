import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SortingProfiler extends JFrame {
    private JButton btnGenerar;
    private JLabel lblEstado;
    private int[] arr;

    public SortingProfiler() {
        setTitle("Sorting Profiler");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnGenerar = new JButton("Generar Números Aleatorios");
        lblEstado = new JLabel("");

        btnGenerar.addActionListener(e -> {
            generarYOrdenar();
            escribirNumerosEnArchivo(arr);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(btnGenerar);
        panel.add(lblEstado);

        add(panel);
    }

    private void generarYOrdenar() {
        lblEstado.setText("Generando números aleatorios...");

        arr = generarNumeros();
        lblEstado.setText("Números generados. Ordenando...");

        long[] timesGnomeSort = new long[300];
        long[] timesMergeSort = new long[300];

        int index = 0;
        for (int i = 10; i <= 3000; i += 10) {
            int[] arrCopy = Arrays.copyOf(arr, i);

            long startTime = System.nanoTime();
            gnomeSort(arrCopy);
            long endTime = System.nanoTime();
            long duration = TimeUnit.NANOSECONDS.toMicros(endTime - startTime);
            timesGnomeSort[index] = duration;

            arrCopy = Arrays.copyOf(arr, i);
            startTime = System.nanoTime();
            mergeSort(arrCopy);
            endTime = System.nanoTime();
            duration = TimeUnit.NANOSECONDS.toMicros(endTime - startTime);
            timesMergeSort[index] = duration;

            index++;
        }

        lblEstado.setText("Ordenamiento completado.");
    }

    // Función para generar números enteros aleatorios y guardarlos en un array
    public int[] generarNumeros() {
        int[] numeros = new int[3000];
        Random rand = new Random();
        for (int i = 0; i < 3000; i++) {
            numeros[i] = rand.nextInt(10000);
        }
        return numeros;
    }

    // Función para escribir los números en un archivo
    public void escribirNumerosEnArchivo(int[] numeros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("numeros.txt"))) {
            for (int numero : numeros) {
                writer.write(numero + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Resto de tu código...

    // Funciones de los algoritmos de ordenamiento
    public void gnomeSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (i == 0 || arr[i - 1] <= arr[i]) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                i--;
            }
        }
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingProfiler profiler = new SortingProfiler();
            profiler.setVisible(true);
        });
    }

    public int[] leerNumerosDelArchivo(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'leerNumerosDelArchivo'");
    }
}


