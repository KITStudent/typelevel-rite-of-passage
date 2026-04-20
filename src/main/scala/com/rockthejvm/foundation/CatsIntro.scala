package com.rockthejvm.foundation

object CatsIntro {


  /*
  In Scala gibt es ==, aber das hat zwei Nachteile:
  1) Es ist nicht typsicher (du kannst theoretisch sehr viel vergleichen, teilweise auch unsinnige Dinge.)
  2) Bei Case Classes ist die Standard-Equality strukturell: alle Felder zählen

  Cats bietet Eq[A] als Type Class an:
    Eq[A] ist ein Beweis/Dictionary, wie man zwei Werte vom Typ A sinnvoll vergleicht
  Damit bekommst du u.a.:
    - typsicherer Gleichheit (=== statt ==)
    - eine klare, kontrollierte Definition von Equaloty pro Typ



   */

  // Eq
  // val aComparison = 2 == "a string" -- wrong, will trigger a compiler warning, will always return false

  // part 1 - type class import
  import cats.Eq

  // part 2 - import Type Classes instances for the types you need
  import cats.instances.int._

  // part 3 - use the Type Class API
  val intEquality = Eq[Int]
  val aTypeSafeComparision = intEquality.eqv(2, 3)
  //println(aTypeSafeComparision)
  //val anUnsafeComparision = intEquality.eqv(2, "a string")

  // part 4 - use extension methods (if applicable)
  import cats.syntax.eq._
  val anotherTypeSafeComp = 2 === 3
  println(anotherTypeSafeComp)
  val neqComparison = 2 =!= 3
  println(neqComparison)
  // val invalidComparison = 2 === "a String" -- doesn't compile!
  // extension methods are only visible in the presence of the right TC instance

  // part5 - extending the Type Class operations to composite types, e.g. lists
  import cats.instances.list._
  val aListComparision = List(2) === List (3)
  println(aListComparision)

  // part 6 - create a TC instance for a custom type
  case class ToyCar(model: String, price: Double)
  // Zwei ToyCar sind gleich, wenn ihre Preise gleich sind (Model ist egal)
  implicit val toyCarEq: Eq[ToyCar] = Eq.instance[ToyCar]{
    (car1, car2) => car1.price == car2.price
  }

  /*
  implicit bedeutet: Dieser Wert darf vom Compiler automatisch verwendet werden, wenn irgendwo ein Eq[ToyCar] benötigt wird.
   */

  val compareTwoToyCars = ToyCar("Ferrari", 29.99) === ToyCar("Mercedes", 29.99)

  def main(args: Array[String]): Unit = {

  }


}
