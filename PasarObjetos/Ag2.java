/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.PasarObjetos;

import agentes.Comunicacion.*;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HenryPaz
 */
public class Ag2 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            try {
                ACLMessage acl = blockingReceive();
                Cliente c = (Cliente) acl.getContentObject();
                System.out.println("Recibí el Cliente: " + c.getNombre() + " " + c.getApellido() + " y vive en: " + c.getDireccion());
                doDelete();
            } catch (UnreadableException ex) {
                Logger.getLogger(Ag2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
