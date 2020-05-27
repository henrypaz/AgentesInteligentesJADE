/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

/**
 *
 * @author Juan Diego
 */
import contenedor.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
public class Agente2 extends Agent {
    private Contenedor c;
    //------------------- INICIALIZAR AGENTE----------------------------------------------------- 
    @Override
    protected void setup() {
        // es para abrir cerrar puertos y liberar recursos
      // addBehaviour(new ComportamientoAgente1());
        addBehaviour(new ComportamientoAgente2Dos());
        super.setup(); //To change body of generated methods, choose Tools | Templates.
    }
    //-----------------------------------------------------R------------------------------------ 
    @Override
    //--------------------ULTIMAS PALABRAS ANTES DE MORIR------------------------------------ 
    protected void takeDown() {
        c.crearHijos("AgenteH", new Object[]{c,1});
        //morirse significa liberar recursos porque ocupa archivos etc
        System.out.println("Soy el Agente 2 y eh muerto(");  
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
    class ComportamientoAgente2Dos extends SimpleBehaviour{
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
                  System.out.println("ESTO ES EL MENSAJE: "+ msj);  
                  
                  c = (Contenedor)getArguments()[0]; 
                  
                  
                  ACLMessage msj2 = new ACLMessage(ACLMessage.INFORM);
                  msj2.addReceiver(msj.getSender()); // QUIEN RECIBE EL MENSAJE
                  msj2.setSender(getAID());
                  msj2.setContent("mori AgenteH");
                  msj2.setConversationId("ag2 to ag1");
                  msj2.setLanguage("Spanish");
                  send(msj2);
                  //---------------------------------------------------
            bandera = true;
            //DO DELETE pausa los hilos uno a uno, libera los recursos
            doDelete();
        }
        //CONDICION PARA TERMINAR
        @Override
        public boolean done() {
           return bandera;
        }
    //------------------------------------------------------------------------- 
    
    }
}