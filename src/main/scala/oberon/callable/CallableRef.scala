package oberon.callable

import oberon.Environment._

class CallableRef(val id: String)  {
  def eval() : Callable = lookupCallable(id) match {
      case Some(v) => v
      case _ => {
        throw new RuntimeException("Function/Procedure: " + id + " was not declared.")
      }
  }
}