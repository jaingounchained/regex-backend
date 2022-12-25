package com.personal.regex.regex_evaluator.impl

import cats.{Applicative, Functor}
import com.personal.regex.regex_evaluator.core.result.RegexEvaluationResult

/**
 * Created by Bhavya Jain.
 * 2022-12-25
 */
private[impl] sealed trait RegexEvaluation[+A] extends Any

private[impl] object RegexEvaluation {

  type Effect = Applicative[RegexEvaluation] with Functor[RegexEvaluation]

  final case class Continue[+A](value: A) extends AnyVal with RegexEvaluation[A]

  final case class Invalid(message: String) extends RegexEvaluation[Nothing]

  def toEvaluationResult[BigDecimal](evaluation: RegexEvaluation[BigDecimal]): RegexEvaluationResult =
    evaluation match {
      case Continue(value) => RegexEvaluationResult.EvaluatedValue(value)
      case Invalid(message) => RegexEvaluationResult.Invalid(message)
    }

  implicit val effect: Effect = new Applicative[RegexEvaluation] with Functor[RegexEvaluation] {

    override def pure[A](x: A): RegexEvaluation[A] = Continue(x)

    override def map[A, B](fa: RegexEvaluation[A])(f: A => B): RegexEvaluation[B] =
      fa match {
        case Continue(value) => Continue(f(value))
        case Invalid(message) => Invalid(message)
      }

    override def map2[A, B, Z](fa: RegexEvaluation[A], fb: RegexEvaluation[B])(f: (A, B) => Z): RegexEvaluation[Z] =
      fa match {
        case Continue(l) =>
          fb match {
            case Continue(r) => Continue(f(l, r))
            case r: Invalid => r
          }

        case l: Invalid => l
      }

    override def ap[A, B](ff: RegexEvaluation[A => B])(fa: RegexEvaluation[A]): RegexEvaluation[B] = ???
  }
}