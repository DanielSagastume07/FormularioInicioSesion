/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Daniel Sagastume
 */

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FondoAuroraPerspectivaJPanel extends JPanel implements ActionListener {

    private Timer timer;
    private double tiempo = 0;

    public FondoAuroraPerspectivaJPanel() {
        super();
        // Fondo muy oscuro (casi negro con un levísimo tono morado)
        this.setBackground(new Color(8, 5, 15)); 
        
        // Temporizador a 30ms (aprox 33 FPS) para un movimiento muy suave
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        // --- PALETA DE COLORES NEÓN ---
        
        // Capa 1: Cian Neón / Azul Eléctrico (Fondo profundo, más lenta)
        // Valores RGB: 0, 200, 255 | Transparencia: 50
        dibujarAuroraDesdeCentro(g2d, ancho, alto, new Color(0, 200, 255, 50), tiempo * 0.02, alto / 2 + 50, 20, 0.004);
        
        // Capa 2: Fucsia / Rosa Neón (Capa media)
        // Valores RGB: 255, 0, 150 | Transparencia: 80
        dibujarAuroraDesdeCentro(g2d, ancho, alto, new Color(255, 0, 150, 80), tiempo * 0.04 + 100, alto / 2 + 80, 30, 0.006);
        
        // Capa 3: Morado "Nova" Original (Capa frontal, más visible)
        // Valores RGB: 191, 160, 255 | Transparencia: 120
        dibujarAuroraDesdeCentro(g2d, ancho, alto, new Color(191, 160, 255, 120), tiempo * 0.06 + 50, alto / 2 + 100, 40, 0.008);
    }

    private void dibujarAuroraDesdeCentro(Graphics2D g2d, int ancho, int alto, Color colorBase, double fase, int yBase, int amplitudCentro, double frecuencia) {
        Path2D forma = new Path2D.Double();
        double centroX = ancho / 2.0;

        forma.moveTo(0, alto); // Empezar en la base izquierda

        // Recorrer la pantalla desde el centro hacia los lados
        for (int x = 0; x <= ancho + 10; x += 5) {
            
            double distanciaAlCentro = Math.abs(x - centroX);
            
            // Efecto de perspectiva (crece hacia los bordes)
            double amplitudPerspectiva = amplitudCentro + (distanciaAlCentro * 0.20);
            
            // Inclinación hacia arriba en los bordes
            double yPerspectiva = yBase - (distanciaAlCentro * 0.35);

            // Movimiento Radial
            double faseRadial = (distanciaAlCentro * frecuencia) - fase;

            // Coordenada Y final con ondas senoidales
            double y = yPerspectiva + Math.sin(faseRadial) * amplitudPerspectiva;
            y += Math.cos(faseRadial * 1.5) * (amplitudPerspectiva * 0.3); // Turbulencia suave

            if (x == 0) {
                forma.moveTo(x, y);
            } else {
                forma.lineTo(x, y);
            }
        }
        
        // Cerrar la figura por debajo
        forma.lineTo(ancho, alto);
        forma.lineTo(0, alto);
        forma.closePath();

        // Degradado: intenso arriba, transparente abajo
        int yTop = (int) (yBase - (centroX * 0.35) - amplitudCentro - 50);
        Color colorTransparente = new Color(colorBase.getRed(), colorBase.getGreen(), colorBase.getBlue(), 0);
        
        GradientPaint degradado = new GradientPaint(0, Math.max(0, yTop), colorBase, 0, alto, colorTransparente);
        
        g2d.setPaint(degradado);
        g2d.fill(forma);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- CAMBIO DE VELOCIDAD AQUÍ ---
        // Antes era 1.0. Al bajarlo a 0.3, la animación es mucho más lenta y relajante.
        tiempo += 1.0; 
        repaint();
    }
}