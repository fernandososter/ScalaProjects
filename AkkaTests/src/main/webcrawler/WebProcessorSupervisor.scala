package webcrawler

import akka.actor.Actor
import akka.actor.Props
import webcrawler.WebProcessorActor

class WebProcessorSupervisor(dbWriterSupervisor: Props) 
extends Actor{
  
  val dbWriterSupervisorRef = context.actorOf(dbWriterSupervisor)
  val webProcessor = Props(new WebProcessorActor(dbWriterSupervisorRef))
  val webProcessorRef = context.actorOf(webProcessor)
  
  def receive = {
    case m => webProcessorRef forward m
  }
  
  
}