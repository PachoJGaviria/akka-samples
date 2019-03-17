package sample.hello;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import static sample.hello.Message.DONE;
import static sample.hello.Message.DOWN_SYSTEM;
import static sample.hello.Message.GREET;

public class HelloWorld extends AbstractActor {

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .matchEquals(DOWN_SYSTEM, m -> {
          // when the printer send the down system message, stop this actor and with it the application
          getContext().stop(self());
        })
        .matchEquals(DONE, m -> {
          System.out.println("Some greeter finished the job!");
        })
        .build();
  }

  @Override
  public void preStart() {
    for (int index = 1; index <= 3; index++) {
      createGreeterActor(index);
    }

    // create the printer actor
    final ActorRef printer = getContext().actorOf(Props.create(Printer.class), "printer");
    // tell it to perform the print process
    printer.tell(DONE, self());
  }

  private void createGreeterActor(int id) {
    // create the greeter actor
    final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class, id), "greeter" + id);
    // tell it to perform the greeting
    greeter.tell(GREET, self());
  }
}
