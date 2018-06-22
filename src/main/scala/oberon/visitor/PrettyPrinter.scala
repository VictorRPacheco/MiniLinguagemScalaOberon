package oberon.visitor

import oberon.expression._
import oberon.command._
import oberon.callable.{Procedure, Function}

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

  def visit(e: FunctionExpression)  : Unit = {
    val args = visitList(e.args)
    str = e.id + "(" + args
  }

  def visit(c: VariableDefinition) : Unit = {
    var datatype = ""
    if (c.variable.dataType.equals(TInt()))
      datatype = "Int"
    else
      datatype = "Boolean"

    str = datatype + " " + c.variable.id + " = " + c.variable.dataValue
  }

  def visit(c: BlockCommand)  : Unit = {
    for(s <- c.cmds){
      str = str + s.accept(this) + "\n"
    }
  }

  def visit(c: Assignment)    : Unit = {
    str = c.id + c.expression.accept(this)
  }

  def visit(c: While)         : Unit = {
    str = "While(" + c.cond.accept(this) + ") {\n"

    str = str + c.command.accept(this)

    str = str + "\n}"
  }

  def visit(c: Print)         : Unit = {
    str = "Print(" + c.expression.accept(this) + ")"
  }

  def visit(c: IfThen)        : Unit = {
    str = "if(" + c.cond.accept(this) + ") \n "
    str = str + c.command.accept(this)
  }

  def visit(c: IfElse)        : Unit = {
    str = "if(" + c.cond.accept(this) + "){\n"
    str = str + "  " + c.ifCommand.accept(this) + "}\n"
    str = str + "else {\n  " + c.elseCommand.accept(this) + "\n}"
  }

  def visit(c: For)           : Unit = {
    str = "for(" + c.initCommand.accept(this)
    str = str + "; " + c.cond.accept(this)
    str = str + ";" + c.lastCommand.accept(this) + ") {\n"
    str = str + c.command.accept(this) + "\n}"
  }


  def visit(c: Return)        : Unit = {
    str = "return " + c.expression.accept(this)
  }

  def visit(c: ProcedureCall) : Unit = {
    val args = visitList(c.args)
    str = c.id + "(" + args
  }

  def visit(c: CallableDeclaration) : Unit = {
    c.callable match {
      case c: Procedure => {
        str = c.procType + " procedure " + c.id + "(" + visitListWithType(c.args) + "; " + c.ret.id + " : " + c.ret.dataType + ")"
      }
      case c: Function => {
        str = c.funcType + " function " + c.id + "(" + visitListWithType(c.args) + ")"
      }
    }
  }

  def visit(c: ReadInt) : Unit = { }
  def visit(c: ReadBool) : Unit = { }

  private def visitBinExp(e: BinExpression) : (String, String) = {
    e.lhs.accept(this)
    val l = str

    e.rhs.accept(this)
    val r = str

    return (l, r)
  }

  private def visitList(e: List[Expression]) : String = {
    var n = 0
    var newString = ""
    while (e.size > n) {
      e(n).accept(this)
      if (e.size != n + 1)
        newString = newString + str + ", "
      else newString = newString + str + ")"
      n+=1
    }
    newString
  }

  private def visitListWithType(e: List[(String, Type)]) : String = {
    var n = 0
    while (e.size > n) {
      str = str + e(n)._1 + " :" + e(n)._2
      if (n + 1 != e.size)
        str = str + ", "
      n+=1
    }
    str
  }
}
