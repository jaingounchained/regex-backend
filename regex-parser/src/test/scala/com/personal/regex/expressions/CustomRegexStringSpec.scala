package com.personal.regex.expressions

import com.personal.regex.ParserSpec
import com.personal.regex.core.model.SyntaxTree.{BinaryAddition, BinaryDivision, BinaryMultiplication, BinarySubtraction, Constant}
import com.personal.regex.impl.DefaultRegexParser

/**
 * Created by Bhavya Jain.
 * 2022-12-24
 */
/*
    For testing parsing of any custom regex
 */
class CustomRegexStringSpec extends ParserSpec {

  val parser = new DefaultRegexParser()

  it should "parser custom regex 1" in {
    val regex = "1+2"
    parser.parse(regex) should matchParseResult(BinaryAddition(Constant(1), Constant(2)))
  }

  it should "parser custom regex 11" in {
    val regex = "1 + 2"
    parser.parse(regex) should matchParseResult(BinaryAddition(Constant(1), Constant(2)))
  }

  it should "parser custom regex 2" in {
    val regex = "1-2"
    parser.parse(regex) should matchParseResult(BinarySubtraction(Constant(1), Constant(2)))
  }

  it should "parser custom regex 22" in {
    val regex = "-1.1-2.2"
    parser.parse(regex) should matchParseResult(BinarySubtraction(Constant(-1.1), Constant(2.2)))
  }

  it should "parser custom regex 3" in {
    val regex = "4/2"
    parser.parse(regex) should matchParseResult(BinaryDivision(Constant(4), Constant(2)))
  }

  it should "parser custom regex 4" in {
    val regex = "4*2"
    parser.parse(regex) should matchParseResult(BinaryMultiplication(Constant(4), Constant(2)))
  }
}
