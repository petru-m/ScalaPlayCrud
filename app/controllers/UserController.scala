package controllers

import dao.UserDAO
import model.{Address, User}
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



/**
 * Created by petru on 08.10.2015.
 */

class UserController  extends Controller{
 
  lazy implicit val userJson = Json.format[User]
  lazy implicit val addressJson = Json.format[Address]

  def getUsers=Action.async{implicit request=>
    UserDAO.all().map{user=>
      Ok(Json.toJson(Map("total" -> Json.toJson(user.length), "records" ->Json.toJson(user))))}
  }
  def saveUser = Action.async(parse.json){implicit  request=>
      request.body.validate[User].map{
      user=>UserDAO.add(user).map(result=>Ok(Json.toJson(result)))
    }.getOrElse(Future.successful(BadRequest("invalid json")))

  }
  def getUser(userID:Int) = Action.async{
    implicit request =>UserDAO.findUserById(userID).map{
      user=>Ok(Json.toJson(user))
    }
  }
  def getUserAddress(addressId:Int) = Action.async{
    implicit request => UserDAO.findUserAddress(addressId).map{
      userAddress=>Ok(Json.toJson(userAddress))
    }
  }
  def deleteUser(userId:Int) = Action.async{
    implicit request => UserDAO.deleteUserById(userId).map{
      delete=>Ok(Json.toJson("User deleted"))
    }
  }
  def updateUser(userID:Int) =  Action.async(parse.json){implicit  request=>
    request.body.validate[User].map{
      user=>UserDAO.updateUser(userID,user).map(result=>Ok(Json.toJson("User updated")))
    }.getOrElse(Future.successful(BadRequest("invalid json")))
  }
}
