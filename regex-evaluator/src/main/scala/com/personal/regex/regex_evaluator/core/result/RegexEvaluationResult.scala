package com.personal.regex.regex_evaluator.core.result

import com.personal.regex.regex_evaluator.core.external.VariableContext

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
sealed trait RegexEvaluationResult extends Any

object RegexEvaluationResult {

  sealed trait Conclusive extends Any with RegexEvaluationResult
  sealed trait Valid extends RegexEvaluationResult.Conclusive

  /**
   * Regex was successfully evaluated
   * @param result final result of regex
   */
  case class EvaluatedValue(result: BigDecimal) extends RegexEvaluationResult.Valid

  case class Partial(f: VariableContext => RegexEvaluationResult.Conclusive) extends RegexEvaluationResult

  /**
   * Regex could not be successfully evaluated
   * @param message error message
   */
  case class Invalid(message: String) extends RegexEvaluationResult.Valid
}
