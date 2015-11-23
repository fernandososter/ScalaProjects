package webcrawler

import akka.actor.Actor
import akka.actor.Props

class WebDbWriterSupervisor extends Actor{
  val dbWriter = Props(new WebDbWriterActor())
  val dbWriterRef = context.actorOf(dbWriter)
  
  def receive = {
    case m => dbWriterRef forward m
  }
  
}