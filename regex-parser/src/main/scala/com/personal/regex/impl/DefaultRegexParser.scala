package com.personal.regex.impl

import com.personal.regex.core.RegexParser
import com.personal.regex.core.RegexParser.ParseResult
import com.personal.regex.core.model.SyntaxTree
import com.personal.regex.core.model.SyntaxTree.{BinaryOperation}
import com.personal.regex.core.model.regex.Regex
import fastparse._

final class DefaultRegexParser extends RegexParser {
  import fastparse.SingleLineWhitespace._

  private val lit = new LiteralParser()

  override def parse(regexString: String): ParseResult =
  fastparse.parse(regexString, regex(_)) match {
      case fail: fastparse.Parsed.Failure =>
        val trace = fail.trace()
        Left(RegexParser.ParseError(input = regexString, failIndex = trace.index, message = trace.longAggregateMsg))
      case success: fastparse.Parsed.Success[Regex] =>
        Right(success.value)
    }

  def regex[_: P]: P[Regex] =
    P(Start ~/ syntaxTree ~/ End)
      .map(syntaxTree => Regex(syntaxTree))

  def syntaxTree[_: P]: P[SyntaxTree] = binaryOperation

  def binaryOperation[_: P]: P[BinaryOperation] = {
    import com.personal.regex.core.model.SyntaxTree._
    P(lit.constant).flatMap{ l =>
      P("+" ~/ lit.constant).map(r => BinaryAddition(l, r)) |
      P("-" ~/ lit.constant).map(r => BinarySubtraction(l, r)) |
      P("*" ~/ lit.constant).map(r => BinaryMultiplication(l, r)) |
      P("/" ~/ lit.constant).map(r => BinaryDivision(l, r))
    }
  }

  //  def addSub[_: P]: P[BinaryOperation] =
//    P(regex).flatMap { l =>
//      P("+" ~~ regex).map(Addition(l, _)) |
//      P("-" ~~ regex).map(Subtraction(l, _))
//    }
//
//  def literal[_: P]: P[Literal] =
//    P(value.numeric).map(Constant) |
//    P(value.variableString).map(Variable)
}