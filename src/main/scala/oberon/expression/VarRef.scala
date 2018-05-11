package oberon.expression

import oberon.Environment._

class VarRef(val id: String) extends Expression {
  override
  def eval() : Value = lookup(id) match {
    case Some(v) => v
    case _       => Undefined()
  }

}