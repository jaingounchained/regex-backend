package com.personal.regex.regex_evaluator.impl

import com.personal.regex.regex_evaluator.core.result.RegexEvaluationResult

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
private[impl] object syntax  {

  final implicit class RegexEvaluationContinueSyntax[A](val v: A) extends AnyVal {

    def continueEvaluation: RegexEvaluation[A] =
      RegexEvaluation.Continue(v)
  }

  final implicit class RegexEvaluationInvalidSyntax[A](val message: String) extends AnyVal {

    def invalidEvaluation: RegexEvaluation[A] =
      RegexEvaluation.Invalid(message)
  }

  final implicit class RegexBigDecimalEvaluationResult(val v: RegexEvaluation[BigDecimal]) extends AnyVal {

    def finalEvaluationResult: RegexEvaluationResult =
      RegexEvaluation.toEvaluationResult(v)
  }
}
