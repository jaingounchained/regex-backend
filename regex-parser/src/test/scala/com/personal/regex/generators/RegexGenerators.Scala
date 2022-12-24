package com.personal.regex.generators

import com.personal.regex.core.model.regex.Regex
import com.personal.regex.core.model.regex.Regex._
import org.scalacheck.Gen

object RegexGenerators {

  def genRegex: Gen[Regex] =
    Gen.frequency(
      (1, genBinaryOperator),
      (5, genLiteral)
    )

  val genBinaryOperator: Gen[BinaryOperation] = {
    for {
      left <- genRegex
      right <- genRegex
      binaryOperation <- Gen.oneOf(
        Addition(left, right),
        Subtraction(left, right),
        Multiplication(left, right),
        Division(left, right)
      )
    } yield binaryOperation
  }

  val genLiteral: Gen[Literal] =
    Gen.frequency(
      (1, genVariable),
      (5, genConstant)
    )

  val genConstant: Gen[Constant] =
    genBigDecimal.map(Constant)

  val genBigDecimal: Gen[BigDecimal] =
    Gen.frequency(
      (50, Gen.oneOf(Gen.negNum[Int].map(BigDecimal.apply), Gen.negNum[Double].map(BigDecimal.apply))),
      (1, Gen.oneOf(Gen.const(0).map(BigDecimal.apply), Gen.const(0.0).map(BigDecimal.apply))),
      (50, Gen.oneOf(Gen.posNum[Int].map(BigDecimal.apply), Gen.posNum[Double].map(BigDecimal.apply))),
    )

  val genVariable: Gen[Variable] =
    genVariableName.map(Variable)

  val genVariableName: Gen[VariableName] =
    Gen.oneOf('a' to 'z').toString
}