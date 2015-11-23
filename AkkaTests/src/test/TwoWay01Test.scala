

import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import akka.actor.Props

class TwoWay01Test extends TestKit(ActorSystem("testesystem")) 
with WordSpecLike
with MustMatchers
{
  
  "Teste de envio/recebimento de mensagem deve: " must {
    
    "enviar e receber mensagens" in {
      import TwoWayMessageTestActor._
      
      val twoWay = system.actorOf(Props[TwoWayMessageTestActor],"teste")
      
      twoWay ! "teste"
      expectMsg("teste") 
      
    }
    
    
  }
  
}