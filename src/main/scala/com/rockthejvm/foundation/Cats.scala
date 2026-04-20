package com.rockthejvm.foundation

object Cats {

  /*
  type classes
  - Applicative
  - Functor
  - FlatMap
  - Monad
  - ApplicationError/MonadError
   */

  // functor - "mappable" structures
  trait MyFunctor[F[_]] {
    def map[A, B](initialValue: F[A])(f: A => B): F[B]
  }

  import cats.Functor
  import cats.instances.list.*
  val listFunctor = Functor[List]
  val mappedList  = listFunctor.map(List(1, 2, 3))(_ + 1)

  def main(args: Array[String]): Unit = {}

}
