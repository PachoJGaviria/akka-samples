package sample.hello;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

  public static void main(String[] args) {
    //    akka.Main.main(new String[] { HelloWorld.class.getName() });
    ActorSystem system = ActorSystem.create("Hello");
    ActorRef helloWorldActor = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
    system.actorOf(Props.create(Terminator.class, helloWorldActor), "terminator");
  }

}
