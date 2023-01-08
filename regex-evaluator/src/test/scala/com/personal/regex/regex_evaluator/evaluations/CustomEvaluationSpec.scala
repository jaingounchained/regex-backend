package com.personal.regex.regex_evaluator.evaluations

import com.personal.regex.regex_evaluator.PartialEvaluatorSpec
import com.personal.regex.regex_evaluator.core.external.VariableContext
import com.personal.regex.regex_evaluator.core.result.RegexEvaluationResult
import com.personal.regex.regex_evaluator.impl.DefaultRegexEvaluator
import com.personal.regex.regex_parser.core.model.regex.Regex
import com.personal.regex.regex_parser.core.model.SyntaxTree.{BinaryAddition => BA, BinaryDivision => BD, BinaryMultiplication => BM, BinarySubtraction => BS, Constant => C, Variable => V}

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
class CustomEvaluationSpec extends PartialEvaluatorSpec {

  val evaluator = new DefaultRegexEvaluator()

  it should "custom evaluation 1" in {
    val regex: Regex = Regex(BA(C(1), C(2)))
    implicit val ctx: VariableContext = VariableContext.empty

    evaluator(regex) shouldBe RegexEvaluationResult.EvaluatedValue(3)
  }

  it should "custom evaluation 2" in {
    val regex: Regex = Regex(BA(C(1), BM(C(2), C(3))))
    implicit val ctx: VariableContext = VariableContext.empty

    evaluator(regex) shouldBe RegexEvaluationResult.EvaluatedValue(7)
  }

  it should "custom evaluation 3" in {
    val regex: Regex = Regex(BA(C(1), BM(C(2), V("a"))))
    implicit val ctx: VariableContext = VariableContext.empty

    evaluator(regex) shouldBe RegexEvaluationResult.Invalid("a value doesn't exist in variable context")
  }

  it should "custom evaluation 4" in {
    val regex: Regex = Regex(BA(C(1), BM(C(2), V("a"))))
    implicit val ctx: VariableContext = VariableContext(Map[String, BigDecimal]("a" -> 4))

    evaluator(regex) shouldBe RegexEvaluationResult.EvaluatedValue(9)
  }

  it should "custom evaluation 5" in {
    val regex: Regex = Regex(BA(C(1), BD(C(2), C(0))))
    implicit val ctx: VariableContext = VariableContext.empty

    evaluator(regex) shouldBe RegexEvaluationResult.Invalid("Division by zero")
  }
}
