package webcrawler

import akka.actor.Actor
import Mensagens._
import akka.actor.ActorRef

class WebProcessorActor(dbWriteSuperviser: ActorRef )  extends Actor{
  
 def receive = {
   case WebConteudo(conteudo) =>
     println(conteudo)
     dbWriteSuperviser ! WebTitulos("titulo")
 }
  
}