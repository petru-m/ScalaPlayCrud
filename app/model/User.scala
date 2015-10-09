package model

/**
 * Created by petru on 08.10.2015.
 */
case class User
(
  userId:Option[Long] = None,
  first_name:String,
  last_name:String,
  email:String
  )
