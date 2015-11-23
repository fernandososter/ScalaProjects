

import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike

import akka.actor.ActorSystem
import akka.testkit.TestKit

class WebCrawler01Test extends TestKit(ActorSystem("testesystem"))
with WordSpecLike
with MustMatchers
{
  
  "O webcrawler deve: " must {
    
    " receber mensagem de inicio e entrar em loopinfinito" in {
      
  
     }
    
  }
  
  
}