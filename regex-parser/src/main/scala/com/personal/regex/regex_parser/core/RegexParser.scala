package com.personal.regex.regex_parser.core

import com.personal.regex.regex_parser.core.RegexParser.ParseResult
import com.personal.regex.regex_parser.core.model.regex.Regex

object RegexParser {

    case class ParseError(input: String, failIndex: Int, message: String)

    type ParseResult = Either[ParseError, Regex]
}

trait RegexParser {

    def parse(regexString: String): ParseResult
}