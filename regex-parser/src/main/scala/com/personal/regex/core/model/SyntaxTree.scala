package com.personal.regex.core.model

/**
 * Created by Bhavya Jain.
 * 2022-12-24
 */

sealed trait SyntaxTree extends Any

object SyntaxTree {

  sealed trait BinaryOperation extends Any with SyntaxTree
  case class BinaryAddition(left: Literal, right: Literal) extends BinaryOperation
  case class BinarySubtraction(left: Literal, right: Literal) extends BinaryOperation
    case class BinaryMultiplication(left: Literal, right: Literal) extends BinaryOperation
    case class BinaryDivision(left: Literal, right: Literal) extends BinaryOperation

    sealed trait Literal extends SyntaxTree
  //  type VariableName = String
    case class Constant(value: BigDecimal) extends Literal
  //  case class Variable(name: VariableName) extends Literal
}