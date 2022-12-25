package com.personal.regex.regex_parser

import com.personal.regex.regex_parser.core.RegexParser
import com.personal.regex.regex_parser.core.RegexParser.ParseResult
import com.personal.regex.regex_parser.core.model.SyntaxTree
import com.personal.regex.regex_parser.core.model.regex.Regex
import org.scalatest.matchers.{MatchResult, Matcher}

/**
 * Created by Bhavya Jain.
 * 2022-12-24
 */
trait RegexMatcher {

  def matchParseResult(parsedExpression: SyntaxTree): Matcher[RegexParser.ParseResult] =
    Matcher { result: ParseResult =>
      val expected = Regex(parsedExpression)
      MatchResult(
        result == Right(expected),
        result.fold(
          e => s"Regex parser failure.\n Error: $e",
          r => s"Regex doesn't match.\n Actual: $r\n Expected: $expected"
        ),
        result.fold(e => s"Regex parser failure: $e", r => s"Regex matches: $r")
      )
    }
}
