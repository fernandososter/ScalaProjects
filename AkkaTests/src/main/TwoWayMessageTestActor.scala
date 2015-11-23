

import akka.actor.Actor
import akka.actor.ActorLogging



object TwoWayMessageTestActor {
  case class TwoWayMessage(data: String){} 
}

class TwoWayMessageTestActor extends Actor with ActorLogging{
  import TwoWayMessageTestActor._
  
  
  def receive={ 
    //case TwoWayMessage(data) => sender() ! data 
    case msg => sender() ! msg
  }
  
}