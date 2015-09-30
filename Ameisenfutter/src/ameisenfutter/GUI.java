/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ameisenfutter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marc
 */
public class GUI extends JPanel implements Runnable {

    private static Simulation s;
    private static int feldgroesse;
    private final int feldpxl = 2;
    private Thread t;

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    public GUI() {
        s = new Simulation();
        feldgroesse = s.getFeldGroesse() * feldpxl;
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(feldgroesse, feldgroesse));
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);
        setOpaque(false);
        setFocusable(true);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.setColor(new Color(4, 176, 24));
        gr.fillRect(0, 0, feldgroesse, feldgroesse);
        for (int i = 0; i < s.getGesamtAmeisen(); i++) {
            gr.setColor(new Color(0, 0, 0));
            gr.fillRect(s.getAmeisenKolonie()[i].getX()*feldpxl, s.getAmeisenKolonie()[i].getY()*feldpxl, 2, 2);
        }
        for (int i = 0; i < s.getGesamtAmeisen(); i++) {
            gr.setColor(new Color(0, 0, 0));
            gr.fillRect(s.getAmeisenKolonie()[i].getX()*feldpxl, s.getAmeisenKolonie()[i].getY()*feldpxl, 2, 2);
        }
        
    }

    @Override
    public void run() {
        while(true){
        repaint();
        }
        
    }

}
