
package contenedor;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import agente.Agente1;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import agente.Agente2;
import agente.AgenteH;

public class Contenedor {
    //agent controller amd agemt container
    AgentContainer mainContenedor;
    AgentController agentController;
    
    public void inicializaContendor()
    {
    //Rutina de jade 
    //importacion local
    jade.core.Runtime runtime = jade.core.Runtime.instance();
    //NO PUEDO CORRER DOS VECES EL MISMO CODIGO
    runtime.setCloseVM(true);
    //DEBO CREAR UN PROFILE
    //parametros null corre en la maquina que estas 
    //identificador por defecto se autoincrementa
    Profile profile = new ProfileImpl(null,1099,null);
    mainContenedor = runtime.createMainContainer(profile);
    inicializaAgentes();
    }
    
    private void inicializaAgentes() {
        try {
            //
            //parametros
            //puedo enviar una configuracion inical por medio del arreglo de objetos
            //se pone como nickname la funcion de cada agente
            agentController = mainContenedor.createNewAgent("Agente1",Agente1.class.getName(), null);
            agentController.start();
            //OTRA FORMA DE INICIALIZARLO
            mainContenedor.createNewAgent("Agente2",Agente2.class.getName(), new Object[]{this}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
    
    public void crearHijos(String nickname,Object[] conocimiento){
        try {
            mainContenedor.createNewAgent(nickname,AgenteH.class.getName(), conocimiento).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

