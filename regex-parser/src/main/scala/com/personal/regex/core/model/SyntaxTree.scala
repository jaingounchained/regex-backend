package com.personal.regex.core.model

/**
 * Created by Bhavya Jain.
 * 2022-12-24
 */

sealed trait SyntaxTree extends Any

object SyntaxTree {

  sealed trait BinaryOperation extends Any with SyntaxTree
  case class BinaryAddition(left: BigDecimal, right: BigDecimal) extends BinaryOperation
  case class BinarySubtraction(left: BigDecimal, right: BigDecimal) extends BinaryOperation
  //  case class Multiplication(left: Regex, right: Regex) extends BinaryOperation
  //  case class Division(left: Regex, right: Regex) extends BinaryOperation

  //  sealed trait Literal extends Regex
  //  type VariableName = String
  //  case class Constant(value: BigDecimal) extends Literal
  //  case class Variable(name: VariableName) extends Literal
}