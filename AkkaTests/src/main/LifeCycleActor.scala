

import akka.actor.ActorLogging
import akka.actor.Actor

class LifeCycleActor extends Actor with ActorLogging {
  
  System.out.println("Construtor"); 
  
  
  override def preStart() ={ 
   println("prestart") 
  }
  
  override def postStop() ={ 
    println("postStop")
  }
  
  override def preRestart(reason: Throwable, message: Option[Any]) {
    println("preRestart")
    super.preRestart(reason, message)
  }
  
  override def postRestart(reason: Throwable) {
    println("postRestart")
    super.postRestart(reason)
  }
  
  def receive ={ 
   
    case msg => sender() ! msg
    
  }
}