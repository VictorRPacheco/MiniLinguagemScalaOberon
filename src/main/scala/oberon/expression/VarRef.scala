package oberon.expression

import oberon.Environment._
import oberon.callable.Variable
import oberon.visitor.Visitor

class VarRef(val id: String) extends Expression {
  override
  def eval() : Value = lookup(id) match {
    case Some(v) => v.asInstanceOf[Variable].dataValue.eval()
    case _       => Undefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }

  override def calculateType(): Type = {
    var v = lookup(id) match {
      case Some(v) => {
        if(v.asInstanceOf[Variable].dataType.equals("Integer")){
          return TInt()
        }
        if(v.asInstanceOf[Variable].dataType.equals("Boolean")){
          return TBool()
        }
      }
      case _ => { return TUndefined() }
    }

    return TUndefined()
  }
}