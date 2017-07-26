package models

import play.api.data._
import play.api.data.Forms._

import scala.collection.mutable.ListBuffer


case class Item(name: String, price: Int, description: String = "",
                manufacturer: String = "", warranty: Int = 720,
                discount: Int = 0, seller: String = "", image: String = "")

object Item {

  val itemForm: Form[Item] = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> number(strict = true),
      "description" -> text,
      "manufactureer" -> text,
      "warranty" -> number,
      "discount" -> number(0, 100),
      "seller" -> text,
      "image" -> text
    )(Item.apply _)(Item.unapply _))

  val items: ListBuffer[Item] = ListBuffer(
    Item("PS4", 245, "The ultimate in console gaming", "sony", 730),
    Item("XBOX ONE", 245, "The ultimate in console gaming", "Microsoft", 730),
    Item("Samsung Galaxy S8", 245, "The sexiest smartphone available", "Samsung", 730),
  )

}
