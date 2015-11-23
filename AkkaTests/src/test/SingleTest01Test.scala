

import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.WordSpec
import org.scalatest.WordSpecLike
import org.scalatest.MustMatchers
import akka.testkit.TestActorRef

class SingleTest01Test extends TestKit(ActorSystem("testsystem")) 
with WordSpecLike
with MustMatchers
{
 
  "Esse teste deve: " must {
    
    " 1) testar mensagem de sucesso" in {
      import SingleTest._
      
      val actorSingleTestRef = TestActorRef[SingleTest] 
      actorSingleTestRef ! SingleMessageTest("teste")
      actorSingleTestRef.underlyingActor.state must (contain("teste"))
      
      actorSingleTestRef stop 
      
    }
    
  }
  
  
}