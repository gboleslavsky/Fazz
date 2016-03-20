/**
  * Created by gboleslavsky on 3/20/16.
  */
package exercises
object TriangleTypeChecker {

  //define the ADT
  sealed trait TriangleType {
    def value: String
  }

  object TriangleType {

    case object Scalene extends TriangleType {
      val value = "Scalene"
    }

    case object Isosceles extends TriangleType {
      val value = "Isosceles"
    }

    case object Equilateral extends TriangleType {
      val value = "Equilateral"
    }

    case object None extends TriangleType {
      val value = "Not a valid triangle"
    }

  }

  def checkTrianglesType(side1: Int, side2: Int, side3: Int): TriangleType = {
    // Int makes the logic easy to understand, otherwise floating point precision has to be taken care of when comparing

    val maybeTriangle = List(side1, side2, side3).sorted //sorting to make checking invariants easy

    def validTriangle(side1: Int, side2: Int, side3: Int): Boolean = {
      //check that all sides have length > 0 and that the longest side is shorter than the sum of the other two
      maybeTriangle.sum > 2 && maybeTriangle(2) < maybeTriangle(0) + maybeTriangle(1) //list indices work because of the sort
    }

    //short-circuit early so the guard does not have to be remembered while reading the actual logic
    if (!validTriangle(side1, side2, side3)) {
      return TriangleType.None
    }

    maybeTriangle.toSet.size match {
      case 1 => TriangleType.Equilateral
      case 2 => TriangleType.Isosceles
      case _ => TriangleType.Scalene
    }
  }

}