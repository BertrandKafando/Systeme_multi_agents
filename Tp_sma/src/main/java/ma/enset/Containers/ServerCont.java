package ma.enset.Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ServerCont {
    public static void main(String[] args) throws ControllerException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer serverContainer = runtime.createAgentContainer(profile);
        AgentController agentController = serverContainer.createNewAgent("Server", "ma.enset.agents.Server", new Object[]{});
        agentController.start();
    }
}
