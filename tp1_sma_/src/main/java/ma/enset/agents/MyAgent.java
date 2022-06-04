package ma.enset.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;

public class MyAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("***** Initialisation de l'agent *********");
        ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
        addBehaviour(parallelBehaviour);

        /*addBehaviour(new Behaviour() {
            private int cpt=0;
            @Override
            public void action() {
                cpt++;
                System.out.println("Action....."+cpt);
            }

            @Override
            public boolean done() {
                if(cpt==100)
                    return true;
                return false;
            }
        });*/

        /*parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("Action.....");
            }
        });*/
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                System.out.println("Action....");
            }
        });
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,1) {
            @Override
            protected void onTick() {
                System.out.println("***************action tick*********************");
            }
        });
    }

    @Override
    protected void afterMove() {
        System.out.println("****** après migration ********");
    }

    @Override
    protected void beforeMove() {
        System.out.println("***** avant migration ******");
    }

    @Override
    protected void takeDown() {
        System.out.println("****** avant de mourir ");
    }
}
