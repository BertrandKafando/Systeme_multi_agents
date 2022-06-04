package ma.enset.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class MyAgenContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime runtime= Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
        AgentController agentController=agentContainer.createNewAgent("Agent1","ma.enset.agents.MyAgent",new Object[]{"BDDC2"});
        agentController.start();

    }
}
