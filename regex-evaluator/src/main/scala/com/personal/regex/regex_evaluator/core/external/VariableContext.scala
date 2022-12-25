package com.personal.regex.regex_evaluator.core.external

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
object VariableContext {

  val empty: VariableContext = VariableContext(Map.empty)
}

/**
 * External variable data to finish partial regex evaluation
 * @param variableMap map containing variable values
 */
case class VariableContext(variableMap: Map[String, BigDecimal])