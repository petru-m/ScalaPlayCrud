package controllers

import model.User
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import dao.UserDAO
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



/**
 * Created by petru on 08.10.2015.
 */

class UserController  extends Controller{
  val dao = new UserDAO
  lazy implicit val userJson = Json.format[User]

  def getUsers=Action.async{implicit request=>
    dao.all().map{user=>
      Ok(Json.toJson(Map("total" -> Json.toJson(user.length), "records" ->Json.toJson(user))))}
  }
  def saveUser = Action.async(parse.json){implicit  request=>
      request.body.validate[User].map{
      user=>dao.add(user).map(result=>Ok(Json.toJson(result)))
    }.getOrElse(Future.successful(BadRequest("invalid json")))

  }
  def getUser(userID:Int) = Action.async{
    implicit request =>dao.findUserById(userID).map{
      user=>Ok(Json.toJson(user))
    }
  }
  def deleteUser(userId:Int) = Action.async{
    implicit request => dao.deleteUserById(userId).map{
      delete=>Ok(Json.toJson("User deleted"))
    }
  }
}
