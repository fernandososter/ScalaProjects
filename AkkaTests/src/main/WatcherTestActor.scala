

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Terminated
import akka.actor.ActorRef


object WatcherTestActor {
  case class WatchMessage(ref: ActorRef)
}

class WatcherTestActor extends Actor with ActorLogging{
  import WatcherTestActor._
  
  def receive ={ 
    case WatchMessage(ref) =>  context.watch(ref)
    case Terminated(actorRef) => println("recebeu msg de terminated")
  }
  
}