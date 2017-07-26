package controllers

import models.Item
import javax.inject.Inject
import play.api.i18n._
import play.api.mvc._
import helpers.NavbarHelpers

class Application @Inject()(val messagesApi: MessagesApi, val navbarHelpers: NavbarHelpers) extends Controller with I18nSupport{

  //home page navbar helper
  def index(id: Int, isNew: Option[String]) = Action {
    isNew match {
      case None if !Item.items.isEmpty =>
        Ok (views.html.itempage (navbarHelpers.homePage, Item.items, id) (Item.itemForm.fill (Item.items (id) ) ) )
      case _ => Ok (views.html.itempage (navbarHelpers.homePage, Item.items, id) (Item.itemForm))

    }
  }

  def formHandler(id: Int) = Action { implicit request: Request[AnyContent] =>
    val formResult = Item.itemForm.bindFromRequest
    formResult.fold({
      errors =>
        println(errors)
        BadRequest(views.html.itempage(navbarHelpers.homePage, Item.items, id)(errors))
    },{
      item => formHelper(item)
    })
  }

  def deleteItem(id: Int) = Action {
    Item.items.remove(id)
    Redirect(routes.Application.index(isNew = None))
  }

  def formHelper(item: Item): Result = {
    Item.items.filter(_.id == item.id) match{
      case x if x.length == 0 =>
        item.id = (Item.nId+1).toString
        Item.items.append(item)
        Item.nId += 1
      case x if x.length == 1 =>
        x.head.replace(item)
    }
    println(Item.items)
    Redirect(routes.Application.index(isNew = None))
  }


}