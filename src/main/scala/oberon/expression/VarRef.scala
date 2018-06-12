package oberon.expression

import oberon.Environment._
import oberon.callable.Variable

class VarRef(val id: String) extends Expression {
  override
  def eval() : Value = lookup(id) match {
    case Some(v) => v.asInstanceOf[Variable].dataValue.eval()
    case _       => Undefined()
  }

}