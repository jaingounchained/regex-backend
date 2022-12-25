package com.personal.regex.regex_evaluator.impl

import com.personal.regex.regex_evaluator.core.RegexEvaluator
import com.personal.regex.regex_evaluator.core.external.VariableContext
import com.personal.regex.regex_evaluator.core.result.RegexEvaluationResult
import com.personal.regex.regex_evaluator.impl.syntax._
import com.personal.regex.regex_parser.core.model.SyntaxTree
import com.personal.regex.regex_parser.core.model.SyntaxTree._
import com.personal.regex.regex_parser.core.model.regex.Regex

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
final class DefaultRegexEvaluator extends RegexEvaluator {

  private implicit val F: RegexEvaluation.Effect = RegexEvaluation.effect

  override def apply(regex: Regex)(implicit ctx: VariableContext): RegexEvaluationResult =
    interpretSyntaxTree(regex.syntaxTree).finalEvaluationResult

  private def interpretSyntaxTree(syntaxTree: SyntaxTree)(implicit ctx: VariableContext): RegexEvaluation[BigDecimal] =
    syntaxTree match {
      case binaryOperation: BinaryOperation =>
        interpretBinaryOperation(binaryOperation)
      case literal: Literal =>
        interpretLiteral(literal)
    }

  private def interpretBinaryOperation(binaryOperation: BinaryOperation)(implicit ctx: VariableContext): RegexEvaluation[BigDecimal] =
    binaryOperation match {
      case BinaryAddition(left, right) =>
        F.map2(
          interpretSyntaxTree(left),
          interpretSyntaxTree(right)
        )((l, r) => l + r)
      case BinarySubtraction(left, right) =>
        F.map2(
          interpretSyntaxTree(left),
          interpretSyntaxTree(right)
        )((l, r) => l - r)
      case BinaryMultiplication(left, right) =>
        F.map2(
          interpretSyntaxTree(left),
          interpretSyntaxTree(right)
        )((l, r) => l * r)
      case BinaryDivision(left, right) =>
        F.map2(
          interpretSyntaxTree(left),
          interpretSyntaxTree(right)
        )((l, r) => l / r)
    }

  private def interpretLiteral(literal: Literal)(implicit ctx: VariableContext): RegexEvaluation[BigDecimal] =
    literal match {
      case Constant(value: BigDecimal) =>
        value.continueEvaluation
      case Variable(variable: VariableName) =>
        ctx.variableMap.get(variable) match {
          case Some(value) =>
            value.continueEvaluation
          case None =>
            (variable + " value doesn't exist in variable context").invalidEvaluation
        }
    }
}
