package ma.enset.agents;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import ma.enset.Containers.Player1Cont;

public class Player1 extends GuiAgent {

    private int compteur = 0;
    private Player1Cont myGui;
    private Player1 player1 = this;
    static ListView list;
    public ObservableList<String> items = FXCollections.observableArrayList();

    public void onGuiEvent(GuiEvent guiEvent) {
        String message = guiEvent.getParameter(0).toString();
        ACLMessage messageAcl = new ACLMessage(ACLMessage.INFORM);
        messageAcl.addReceiver(new AID(guiEvent.getParameter(1).toString(), AID.ISLOCALNAME));
        list = (ListView) guiEvent.getParameter(2);
        list.setItems(items);
        messageAcl.setContent(message);
        send(messageAcl);
    }

    @Override
    protected void setup() {
        myGui = (Player1Cont) getArguments()[0];
        myGui.setAgent(player1);
        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                if (aclMessage != null) {
                    items.add(aclMessage.getContent());
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
