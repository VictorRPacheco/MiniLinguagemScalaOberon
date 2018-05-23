package oberon.callable

import oberon.Environment._

class CallableRef(val id: String)  {
  def eval() : Unit = lookupTable(id) match {
      case Some(v) => v
      case _       =>

  }
}