package logProcessing

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.PoisonPill

class FileWatcher(sourceUri: String, logProcSupervisor: ActorRef) 
extends Actor with FileWatchingAbilities {
  
  register(sourceUri) 
  
  import FileWatcherProtocol._
  import LogProcessingProtocol._
  
  def receive = {
    case NewFile(file,_) => 
       logProcSupervisor ! LogFile(file) 
      
    case SourceAbandoned(uri) if uri == sourceUri => self ! PoisonPill
  }
}