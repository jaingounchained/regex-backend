package com.personal.regex.expressions

import com.personal.regex.ParserSpec
import com.personal.regex.core.model.SyntaxTree.{
  BinaryAddition => BA,
  BinaryDivision => BD,
  BinaryMultiplication => BM,
  BinarySubtraction => BS,
  Constant => C,
  Variable => V
}
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
    val regex = "(1+2)"
    parser.parse(regex) should matchParseResult(BA(C(1), C(2)))
  }

  it should "parser custom regex 1a" in {
    val regex = "(1 + 2)"
    parser.parse(regex) should matchParseResult(BA(C(1), C(2)))
  }

  it should "parser custom regex 2" in {
    val regex = "(1-2)"
    parser.parse(regex) should matchParseResult(BS(C(1), C(2)))
  }

  it should "parser custom regex 22" in {
    val regex = "(-1.1-2.2)"
    parser.parse(regex) should matchParseResult(BS(C(-1.1), C(2.2)))
  }

  it should "parser custom regex 3" in {
    val regex = "(4/2)"
    parser.parse(regex) should matchParseResult(BD(C(4), C(2)))
  }

  it should "parser custom regex 4" in {
    val regex = "(4*2)"
    parser.parse(regex) should matchParseResult(BM(C(4), C(2)))
  }

  it should "parser custom regex 5" in {
    val regex = "((1+2)-1)"
    parser.parse(regex) should matchParseResult(BS(BA(C(1), C(2)), C(1)))
  }

  it should "parser custom regex 6" in {
    val regex = "((1+2)-(4/2))"
    parser.parse(regex) should matchParseResult(BS(BA(C(1), C(2)), BD(C(4), C(2))))
  }

  it should "parser custom regex 7" in {
    val regex = "((1+2)*((4/2)-1))"
    parser.parse(regex) should matchParseResult(BM(BA(C(1), C(2)), BS(BD(C(4), C(2)), C(1))))
  }

  it should "parser custom regex 9" in {
    val regex = "1"
    parser.parse(regex) should matchParseResult(C(1))
  }

  it should "parser custom regex 10" in {
    val regex = "a"
    parser.parse(regex) should matchParseResult(V("a"))
  }

  it should "parser custom regex 11" in {
    val regex = "C"
    parser.parse(regex) should matchParseResult(V("C"))
  }

  it should "parser custom regex 12" in {
    val regex = "((1+A)*((b/2)-1))"
    parser.parse(regex) should matchParseResult(BM(BA(C(1), V("A")), BS(BD(V("b"), C(2)), C(1))))
  }
}
