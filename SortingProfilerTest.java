import static org.junit.Assert.*;

import org.junit.Test;

public class SortingProfilerTest {

    @Test
    public void testGenerarNumeros() {
        SortingProfiler profiler = new SortingProfiler();
        int[] numeros = profiler.generarNumeros();
        assertEquals(3000, numeros.length);
        for (int num : numeros) {
            assertTrue(num >= 0 && num < 10000);
        }
    }

    @Test
    public void testEscribirYLeerNumeros() {
        SortingProfiler profiler = new SortingProfiler();
        int[] numeros = new int[]{1, 2, 3, 4, 5};
        profiler.escribirNumerosEnArchivo(numeros);
        int[] numerosLeidos = profiler.leerNumerosDelArchivo("numeros.txt");
        assertArrayEquals(numeros, numerosLeidos);
    }
}
