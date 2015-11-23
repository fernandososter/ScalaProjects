package webcrawler

import akka.actor.Actor

class WebDbWriterActor extends Actor {
  
  def receive = {
    case m => println(m)
  }
  
}