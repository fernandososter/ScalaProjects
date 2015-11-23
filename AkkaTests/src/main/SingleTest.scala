




import akka.actor.Actor

object SingleTest{ 
  case class SingleMessageTest(msg: String){}
}

class SingleTest extends Actor{
  import SingleTest._
  var internalState = Vector[String](); 
  
  
  override def preStart()={ println("preStart") }
  
  override def postStop()={println("postStop")} 
  
  
  def receive = {
    case SingleMessageTest(msg) => internalState =  internalState :+ msg
  }
  
  def state = internalState
}