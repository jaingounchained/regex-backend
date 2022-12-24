package com.personal.regex

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

/**
 * Created by Bhavya Jain.
 * 2022-12-24
 */
trait ParserSpec extends AnyFlatSpec with ScalaCheckDrivenPropertyChecks with Matchers with RegexMatcher {

  override implicit val generatorDrivenConfig: PropertyCheckConfiguration =
    PropertyCheckConfiguration(minSuccessful = 10)

  behavior of "Regex Parser"
}
