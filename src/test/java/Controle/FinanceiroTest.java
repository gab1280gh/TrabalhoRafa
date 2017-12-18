/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controle.Financeiro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Moon_
 */
public class FinanceiroTest {
    
    private Financeiro f;
    
    public FinanceiroTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() { 
        f = new Financeiro();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testTaxas(){
        assertEquals(15, f.Taxas(100), 0.00001);
    }
    
}
