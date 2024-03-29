package ma.enset.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;

import java.util.Locale;

public class Server extends Agent {

    @Override
    protected void setup() {
        int magicNumber = (int) (Math.random() * 100);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                if (aclMessage != null) {
                    int usernumber = 0;
                    ACLMessage messageAcl = new ACLMessage(ACLMessage.INFORM);
                    try {
                        usernumber = Integer.parseInt(aclMessage.getContent());
                        if (usernumber == magicNumber) {
                            // get all agents
                            AMSAgentDescription [] agents = null;
                            SearchConstraints c = new SearchConstraints();
                            c.setMaxResults ( new Long(-1) );
                            agents = AMSService.search(Server.this, new AMSAgentDescription(), c);

                            // sending message to all agents
                            for (int i=0; i<agents.length;i++){
                                messageAcl.addReceiver(new AID(agents[i].getName().getLocalName(), AID.ISLOCALNAME));
                            }
                            messageAcl.setContent(aclMessage.getSender().getLocalName().toUpperCase(Locale.ROOT)+" IS THE WINNER | END OF THE GAME!!!");
                            send(messageAcl);
                            doDelete();
                        } else if (usernumber > magicNumber) {
                            messageAcl.addReceiver(new AID(aclMessage.getSender().getLocalName(), AID.ISLOCALNAME));
                            messageAcl.setContent(usernumber+" IS TOO HIGH");
                            send(messageAcl);
                        } else {
                            messageAcl.addReceiver(new AID(aclMessage.getSender().getLocalName(), AID.ISLOCALNAME));
                            messageAcl.setContent(usernumber+" IS TOO LOW");
                            send(messageAcl);
                        }
                    } catch (Exception e) {
                        messageAcl.addReceiver(new AID(aclMessage.getSender().getLocalName(), AID.ISLOCALNAME));
                        messageAcl.setContent("YOU MUST ENTER A NUMBER");
                        send(messageAcl);
                    }
                } else {
                    block();
                }

            }
        });
        System.out.println("*** Agent " + getLocalName() + " started ***");
    }

    @Override
    protected void takeDown() {
        System.out.println("*** Agent " + getLocalName() + " terminated ***");
    }

    @Override
    protected void beforeMove() {
        System.out.println("*** Agent " + getLocalName() + " before move ***");
    }
    @Override
    protected void afterMove() {
        System.out.println("*** Agent " + getLocalName() + " after move ***");
    }

}
