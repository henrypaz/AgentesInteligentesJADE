/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author HenryPaz
 */
public class Ag1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            new EnviarMensaje().enviarMensajeString(ACLMessage.INFORM, "Ag2", getAgent(), "Hola Agente, soy " + getAgent().getName(),
                    "COD001");
        }

    }

}
