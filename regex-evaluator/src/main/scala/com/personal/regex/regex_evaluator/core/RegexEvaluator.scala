package com.personal.regex.regex_evaluator.core

import com.personal.regex.regex_evaluator.core.external.VariableContext
import com.personal.regex.regex_evaluator.core.result.RegexEvaluationResult
import com.personal.regex.regex_parser.core.model.regex.Regex

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
trait RegexEvaluator {

  def apply(regex: Regex)(implicit ctx: VariableContext): RegexEvaluationResult
}
