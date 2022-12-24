package com.personal.regex

import com.personal.regex.core.RegexParser
import com.personal.regex.core.RegexParser.ParseResult
import com.personal.regex.core.model.SyntaxTree
import com.personal.regex.core.model.regex.Regex
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
