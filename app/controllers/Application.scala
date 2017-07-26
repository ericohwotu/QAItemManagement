package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  //home page navbar helper
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}