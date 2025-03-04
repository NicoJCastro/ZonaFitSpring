package gm.zona_fit.gui;

import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;
    IClienteServicio clienteServicio;

    //Constructor
    @Autowired
    public ZonaFitForma(ClienteServicio clienteServicio) {
        //Inyección de dependencias
        this.clienteServicio = clienteServicio;
        iniciarForma();
    }

    public void iniciarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }




}
