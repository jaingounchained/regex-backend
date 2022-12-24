package com.personal.regex.impl

import com.personal.regex.core.model.SyntaxTree.{Constant, Literal, Variable, VariableName}
import fastparse._

/**
 * Created by Bhavya Jain.
 * 2022-07-31
 */
private final class LiteralParser {

  def literal[_: P]: P[Literal] =
    constant | variable

  private def constant[_: P]: P[Constant] =
    P(numeric).map(Constant)

  private def numeric[_: P]: P[BigDecimal] =
    P("-".? ~~ numericString ~~ ".".? ~~ numericString.?).!.map(BigDecimal.exact)

  private def numericString[_: P]: P[String] =
    P(CharsWhileIn("0123456789", 1)).!

  private def variable[_: P]: P[Variable] =
    P(variableString).map(Variable)

  private def variableString[_: P]: P[VariableName] =
    P(CharsWhileIn("a-zA-Z", 1)).!
}
