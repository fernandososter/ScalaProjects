package logProcessing

import akka.actor.Actor
import akka.actor.ActorRef

class LogProcessor(dbSupervisor: ActorRef) extends Actor 
with LogParsing 
{
  
  import LogProcessingProtocol._
  
  def receive = {
    case LogFile(file) => 
        val lines = parse(file)
        lines.foreach(dbSupervisor ! _) 
  }
}