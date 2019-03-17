package sample.hello;

import akka.actor.AbstractActor;

import static sample.hello.Message.DONE;
import static sample.hello.Message.GREET;

public class Greeter extends AbstractActor {

  private int id;

  /**
   * Default constructor
   * @param id int
   */
  public Greeter(int id) {
    this.id = id;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().matchEquals(GREET, m -> {
      System.out.println("Hello World! I am greeter " + id);
      sender().tell(DONE, self());
    }).build();
  }
}
