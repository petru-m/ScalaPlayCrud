package controllers

import dao.AddressDAO
import model.Address
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Petru on 3/15/2016.
  */

class AddressController extends Controller{

  lazy implicit val addressJson = Json.format[Address]

  def getAddresses=Action.async{implicit request =>
    AddressDAO.allAddresses().map{address=>
      Ok(Json.toJson(Json.toJson(address)))}
  }

  def saveAddress=Action.async(parse.json) { implicit request =>
    request.body.validate[Address].map { address =>
      AddressDAO.addAddress(address).map(result => Ok(Json.toJson(result)))
    }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

  def deleteAddress(addressId:Int)=Action.async{implicit request =>
    AddressDAO.deleteAddress(addressId).map{
      delete=>Ok(Json.toJson("Address deleted"))
    }
  }

  def updateAddress(addressId:Int)=Action.async(parse.json){implicit request =>
    request.body.validate[Address].map{address =>
      AddressDAO.updateAddress(addressId,address).map(result =>
        Ok(Json.toJson(address)))
    }.getOrElse(Future.successful(BadRequest("invalid json")))
  }
}
