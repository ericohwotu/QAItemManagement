package models

import play.api.data._
import play.api.data.Forms._


case class Item(name: String, price: Int, description: String = "",
                manufacturer: String = "", warranty: Int = 720,
                 discount: Int = 0, seller: String = "", image: String = "")

object Item{

  val me = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> number,
      "description" -> text,
      "manufactureer" -> text,
      "warranty" -> number,
      "discount" -> number(0,100),
      "seller" -> text,
      "image" -> text
    )(Item.apply _)(Item.unapply _)
  )
}
