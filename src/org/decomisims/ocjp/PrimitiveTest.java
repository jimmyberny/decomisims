package org.decomisims.ocjp;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class PrimitiveTest {

    private int w;
    private int[] arr = new int[4];

    public PrimitiveTest() {
    }

    public void test() {
        int x = 9;
        int y;
        int z;
        if (x > 10) {
            y = 10;
        }
        z = x + w;
        for (int i = 0; i < arr.length; i++) {
            z = arr[i];
        }
        System.out.println(z);
    }

    public static void main(String[] args) {
        PrimitiveTest pt = new PrimitiveTest();
        pt.test();
    }
}
