package oberon.visitor

import oberon.expression._
import oberon.command._

class PrettyPrinter extends Visitor {
  var str = ""

  def visit(e: Undefined)     : Unit = { str = "undefined" }

  def visit(e: IntValue)      : Unit = { str = e.value.toString }

  def visit(e: BoolValue)     : Unit = { str = e.value.toString }

  def visit(e: AddExpression) : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " + " + r + ")"
  }

  def visit(e: MinusExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " - " + r + ")"
  }

  def visit(e: MultExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " * " + r + ")"
  }

  def visit(e: DivideExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " / " + r + ")"
  }

  def visit(e: SmallerEqExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " <= " + r + ")"
  }

  def visit(e: SmallerExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " < " + r + ")"
  }

  def visit(e: BiggerEqExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " >= " + r + ")"
  }

  def visit(e: BiggerExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " > " + r + ")"
  }

  def visit(e: EqExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " == " + r + ")"
  }

  def visit(e: DifExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " != " + r + ")"
  }

  def visit(e: OrExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " || " + r + ")"
  }

  def visit(e: AndExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " & " + r + ")"
  }

  def visit(e: NotExpression) : Unit = {
    var v = e.uhs.accept(this)
    str = "(!" + v + ")"
  }

  def visit(e: UnitExpression) : Unit = {
    var v = e.uhs.accept(this)
    str = "(" + v + ")"
  }

  def visit(e: VarRef)        : Unit = { }
  def visit(e: FunctionExpression)  : Unit = { }

  def visit(c: VariableDefinition) : Unit = { }
  def visit(c: BlockCommand)  : Unit = { }
  def visit(c: Assignment)    : Unit = { }
  def visit(c: While)         : Unit = { }
  def visit(c: Print)         : Unit = { }
  def visit(c: IfThen)        : Unit = { }
  def visit(c: IfElse)        : Unit = { }
  def visit(c: For)           : Unit = { }
  def visit(c: Return)        : Unit = { }

  private def visitBinExp(e: BinExpression) : (String, String) = {
    e.lhs.accept(this)
    val l = str

    e.rhs.accept(this)
    val r = str

    return (l, r)
  }

}
