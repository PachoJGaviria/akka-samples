package sample.hello;

import akka.actor.AbstractActor;

import static sample.hello.Message.DONE;
import static sample.hello.Message.DOWN_SYSTEM;
import static sample.hello.Message.GREET;

public class Printer extends AbstractActor {
  @Override
  public Receive createReceive() {
    return receiveBuilder().matchEquals(DONE, m -> {
      System.out.println("Hi, i am the printer!!");
      sender().tell(DOWN_SYSTEM, self());
    }).build();
  }
}
