package model

/**
  * Created by Petru on 3/15/2016.
  */

case class Address
(
 addressId:Option[Long]=None,
 streetName:String,
 number:Long,
 locality:String,
 city:String,
 country:String

)
