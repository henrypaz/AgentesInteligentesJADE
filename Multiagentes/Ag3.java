/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.Multiagentes;

import agentes.Comunicacion.*;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author HenryPaz
 */
public class Ag3 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage acl = blockingReceive();
            System.out.println("Hola, q gusto "+acl.getSender()+", yo soy "+getAgent().getName());
            new EnviarMensaje().enviarMensajeString(ACLMessage.INFORM, "Ag4", getAgent(), "Hola Agente, soy " + getAgent().getName(),
                    "COD001");
        }

    }
}
