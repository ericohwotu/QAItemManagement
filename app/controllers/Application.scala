package controllers

import models.Item
import javax.inject.Inject
import play.api.i18n._
import play.api.mvc._

class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{

  //home page navbar helper
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def formHandler = Action { implicit request: Request[AnyContent] =>
    val formResult = Item.itemForm.bindFromRequest

    formResult.fold({
      errors => BadRequest("bad")
    },{
      item => Item.items.append(item)
        Ok("added")
    })
  }

}