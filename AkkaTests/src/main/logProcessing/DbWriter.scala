package logProcessing

import akka.actor.Actor

class DbWriter(connection: Any)  extends Actor {
  import LogProcessingProtocol._
  
  def receive = {
    case Line(time,message,messageType) => 
      println("escrever")
  }
  
}