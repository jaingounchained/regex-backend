package com.personal.regex.impl

import com.personal.regex.core.model.SyntaxTree.Constant
import fastparse._
import com.personal.regex.impl.Syntax.ParsedSyntax

/**
 * Created by Bhavya Jain.
 * 2022-07-31
 */
private final class LiteralParser {

  def constant[_: P]: P[Constant] =
    P(numeric).map(Constant)

  def numeric[_: P]: P[BigDecimal] =
    P("-".? ~~ numericString ~~ ".".? ~~ numericString.?).!.map(BigDecimal.exact)

  private def numericString[_: P]: P[String] =
    P(CharsWhileIn("0123456789", 1)).!

//  def variableString[_: P]: P[VariableName] = ???
}
