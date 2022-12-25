package com.personal.regex.regex_parser.impl

import fastparse.P
/**
 * Created by Bhavya Jain.
 * 2022-07-31
 */
private object Syntax {

  implicit class ParsedSyntax[A](val parser: P[A]) extends AnyVal {

    def to[B](value: B): P[B] =
      parser.map(_ => value)
  }
}
