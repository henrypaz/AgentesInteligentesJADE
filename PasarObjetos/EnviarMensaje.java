/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.PasarObjetos;

import agentes.Comunicacion.*;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry Paz
 */
public class EnviarMensaje {

    public static void enviarMensajeObject(int tipoMsj, String receptor,
            Agent agenteEmisor, Serializable contenido, String conversationID) {
        ACLMessage acl = new ACLMessage(tipoMsj);
        AID id = new AID();
        id.setLocalName(receptor);
        acl.addReceiver(id);
        acl.setSender(agenteEmisor.getAID());
//                    acl.setEncoding("utf8mb4");
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        try {
            acl.setContentObject(contenido);
        } catch (IOException ex) {
            Logger.getLogger(EnviarMensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        acl.setConversationId(conversationID);
        agenteEmisor.send(acl);
    }

    public static void enviarMensajeString(int tipoMsj, String receptor,
            Agent agenteEmisor, String contenido, String conversationID) {
        ACLMessage acl = new ACLMessage(tipoMsj);
        AID id = new AID();
        id.setLocalName(receptor);
        acl.addReceiver(id);
        acl.setSender(agenteEmisor.getAID());
//                    acl.setEncoding("utf8mb4");
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        acl.setContent(contenido);
        acl.setConversationId(conversationID);
        agenteEmisor.send(acl);
    }
}
