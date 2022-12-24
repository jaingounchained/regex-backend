package com.personal.regex.generators

import com.personal.regex.core.model.SyntaxTree
import com.personal.regex.core.model.SyntaxTree._
import org.scalacheck.Gen

object RegexGenerators {

  val genSyntaxTree: Gen[SyntaxTree] =
    Gen.frequency(
      (5, genLiteral),
      (1, genBinaryOperator)
    )

  val genBinaryOperator: Gen[BinaryOperation] = {
    for {
      left <- genSyntaxTree
      right <- genSyntaxTree
      binaryOperation <- Gen.oneOf(
        BinaryAddition(left, right),
        BinarySubtraction(left, right),
        BinaryMultiplication(left, right),
        BinaryDivision(left, right)
      )
    } yield binaryOperation
  }

  val genLiteral: Gen[Literal] = genConstant

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