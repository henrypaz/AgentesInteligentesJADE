/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.StringTokenizer;
//heredo un comportamiento de un hilo, no un hilo
public class Agente1 extends Agent {
    private String aidAgentPadre = "Agente2";
    @Override
    protected void setup() {
        // es para abrir cerrar puertos y liberar recursos
      // addBehaviour(new ComportamientoAgente1());
        addBehaviour(new ComportamientoAgente1Dos());
        super.setup(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    //ULTIMAS PALABRAS ANTES DE MORIR 
    protected void takeDown() {
        //morirse significa liberar recursos porque ocupa archivos etc
        System.out.println("ME MORI SOY EL 1 :(");  
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
    }

    class ComportamientoAgente1Dos extends SimpleBehaviour{
       private boolean bandera = false;
        @Override
        public void action() {
              System.out.println(getName());  
              
              //------------ENVIAR MENSAJES----------------------
              //INFORM PARA SOLO ENVIAR 
              //REQUEST ESPERAR UN MENSAJE 
              ACLMessage msj = new ACLMessage(ACLMessage.REQUEST);
              AID aid = new AID();
              //SE PONE EL NICKNAME
              aid.setLocalName(aidAgentPadre);
              msj.addReceiver(aid); // QUIEN RECIBE EL MENSAJE
              msj.setSender(getAID());
              msj.setContent("Hola Como estas soy el " + getName());
              msj.setConversationId("ag1 to ag2");
              msj.setLanguage("Spanish");
              send(msj);
              //SI EL AGENTE NO RECIBE UN MENSAJE SE QUEDA BLOQUEADO
              
              ACLMessage msj2 = blockingReceive();
              System.out.println(msj2);
              String contenido = msj2.getContent();
              String [] content = contenido.split(" ");
              if(content[0].equalsIgnoreCase("mori")){
                aidAgentPadre = content[1];
              }
              
               //--------------------------------------------------     
        
        //DO DELETE pausa los hilos uno a uno, libera los recursos
        
        }

        @Override
        public boolean done() {
           return bandera;
        }
    
    
    }
}

