package oberon.callable

import oberon.Environment._

class CallableRef(val id: String)  {
  def eval() : Callable = lookupTable(id) match {
      case Some(v) => v
  }
}