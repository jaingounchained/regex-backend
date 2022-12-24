package com.personal.regex.impl

import fastparse._
/**
 * Created by Bhavya Jain.
 * 2022-07-31
 */
private final class LiteralParser {

  def numeric[_: P]: P[BigDecimal] =
    P("-".? ~~ numericString ~~ ".".? ~~ numericString.?).!.map(BigDecimal.exact)

  private def numericString[_: P]: P[String] =
    P(CharsWhileIn("0123456789", 1)).!

//  def variableString[_: P]: P[VariableName] = ???
}
