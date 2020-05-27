/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import contenedor.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Juan Diego
 */
public class AgenteH extends Agent {
    private Contenedor c;
    int hijo=0;
    String nombreAgenteHijo = "";
    @Override
    protected void setup() {
        // es para abrir cerrar puertos y liberar recursos
      // addBehaviour(new ComportamientoAgente1());
        addBehaviour(new ComportamientoAgenteH());
        super.setup(); //To change body of generated methods, choose Tools | Templates.
    }
    //-----------------------------------------------------R------------------------------------ 
    @Override
    //--------------------ULTIMAS PALABRAS ANTES DE MORIR------------------------------------ 
    protected void takeDown() {
        //morirse significa liberar recursos porque ocupa archivos etc
        
        
        c.crearHijos(nombreAgenteHijo, new Object[]{c,hijo});
        System.out.println("Adios soy "+getName());
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
    }
    //----------------------------------------------------------------------------------------- 
    
    
    //-----------------------CYCLICBEHAVIOUR----------------------------------
    /*class ComportamientoAgente2 extends CyclicBehaviour{
        @Override
        public void action() {
                   System.out.println(getName());
        }
    }*/
    //------------------------------------------------------------------------
    
    //-----------------------SIMPLEBEHAVIOUR----------------------------------
    //SIMPLEBEHAVIOUR ES MEJOR PORQUE ALGUN MOMENTO EL AGENTE NECESITA PARAR
    class ComportamientoAgenteH extends SimpleBehaviour{
       private boolean bandera = false;
        //ACCION
        @Override
        public void action() {
            System.out.println(getName());  
                //------------RECIBIR MENSAJES----------------------
                // ACLMessage msj =  blockingReceive();
                // System.out.println("ESTO ES EL MENSAJE "+ msj);  
                //---------------------------------------------------

                //------------RECIBIR Y ENVIAR MENSAJES----------------------
                ACLMessage msj =  blockingReceive();
                //System.out.println("ESTO ES EL MENSAJE "+ msj); 

                c = (Contenedor)getArguments()[0];

                ACLMessage msj2 = new ACLMessage(ACLMessage.INFORM);
                msj2.addReceiver(msj.getSender()); // QUIEN RECIBE EL MENSAJE
                msj2.setSender(getAID());
                msj2.setContent("mori AgenteH"+getArguments()[1].toString());
                msj2.setConversationId("agH to ag1");
                msj2.setLanguage("Spanish");
                send(msj2);
               
                hijo = (int) getArguments()[1];
                nombreAgenteHijo = "AgenteH"+hijo;
                hijo++;
                //---------------------------------------------------
                bandera = true;
                doDelete();
            //DO DELETE pausa los hilos uno a uno, libera los recursos
            
        }
        //CONDICION PARA TERMINAR
        @Override
        public boolean done() {
           return bandera;
        }
    //------------------------------------------------------------------------- 
    
    }
}
