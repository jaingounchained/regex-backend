package com.personal.regex.core

import com.personal.regex.core.RegexParser.ParseResult
import com.personal.regex.core.model.regex.Regex

object RegexParser {

    case class ParseError(input: String, failIndex: Int, message: String)

    type ParseResult = Either[ParseError, Regex]
}

trait RegexParser {

    def parse(regexString: String): ParseResult
}