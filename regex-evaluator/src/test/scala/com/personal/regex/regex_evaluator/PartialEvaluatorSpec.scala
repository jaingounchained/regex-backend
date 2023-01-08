package com.personal.regex.regex_evaluator

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
trait PartialEvaluatorSpec extends AnyFlatSpec with ScalaCheckDrivenPropertyChecks with Matchers with RegexEvaluationMatchers {

  override implicit val generatorDrivenConfig: PropertyCheckConfiguration =
    PropertyCheckConfiguration(minSuccessful = 1000, minSize = 5, sizeRange = 10)

  behavior of "Regex evaluator"
}
