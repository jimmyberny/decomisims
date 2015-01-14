/*
 */
package org.decomisims.ocjp;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class ScopeTest {
    
    private int i = 1;
    
    public void first() {
        int i = 4, j = 5;
        this.i = i + j;
        second(7);
    }
    
    public void second(int i) {
        int j = 8;
        this.i = i + j;
    }
    
    public static void main(String[] args) {
        ScopeTest st = new ScopeTest();
        st.first();
        
        System.out.println(st.i);
    }
}
