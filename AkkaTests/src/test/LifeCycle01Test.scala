

import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.TestKit

class LifeCycle01Test extends TestKit(ActorSystem("testsystem"))
with WordSpecLike
with MustMatchers
{
  
  
  "Teste de lifecycle deve: " must {
    
    " fazer o processo de testar os ciclos de vida" in {
      
       val lifeCycleRef = system.actorOf(Props[LifeCycleActor],"LifeCycle")
       lifeCycleRef ! "MensagemDeTeste"
       lifeCycleRef.tell("msg", testActor)
      expectMsg("msg")
      system.stop(lifeCycleRef)
      Thread.sleep(1000)
    }
    
    "outro teste do ciclo de vida é testar o watcher: " in {
      import WatcherTestActor._
      
       /*
        * Segundo o livro, podemos cadastrar um ator para ficar monitorando 
        * outro ator. Quando o ator monitorado morrer, o que esta monitorando 
        * vai receber uma msg de Terminated. 
        * 
        */
      
       val lifeCycleRef = system.actorOf(Props[LifeCycleActor],"LifeCycle")
       val watcher = system.actorOf(Props[WatcherTestActor],"watcher")
       
       watcher ! WatchMessage(lifeCycleRef)
       
       lifeCycleRef ! "MensagemDeTeste"
       lifeCycleRef.tell("msg", testActor)
       expectMsg("msg")
       system.stop(lifeCycleRef)
       
       Thread.sleep(1000)
      
      
    }
    
  }
  
  
}