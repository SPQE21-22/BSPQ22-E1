package client;
/**
 * Host class.
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import client.GUI.*;

import javax.swing.*;

public class Host{

    public static void main(String[] args){


        String hostname = args[0];
        String port = args[1];

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiLogin(hostname, port);
            }
        });

/*
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiCafeteria();
            }
        });
*/
    }
}
