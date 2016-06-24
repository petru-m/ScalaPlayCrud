package dao

import _root_.slick.driver.JdbcProfile
import model.{Address, User}
import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import play.api.db.slick.HasDatabaseConfig
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by petru on 08.10.2015.
 */

object UserDAO  extends  HasDatabaseConfig[JdbcProfile] {
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  private val Users = TableQuery[UserTable]
  private val Addresses = TableQuery[AddressDAO.AddressTable]

  private class UserTable(tag :Tag) extends Table[User](tag,"user"){
    def recid = column[Long]("userId", O.PrimaryKey, O.AutoInc)
    def first_name = column[String]("first_name")
    def last_name = column[String]("last_name")
    def email = column[String]("email")
    def addressId = column[Long]("addressId")
    def * = (recid.?, first_name, last_name,email,addressId.?) <> (User.tupled, User.unapply _)

    def address = foreignKey("address",addressId,Addresses)(_.addressId)
  }
  def all(): Future[List[User]] = db.run(Users.result).map(_.toList)

  def add(user : User):Future[User]=db.run(Users +=user).map(_=>user)

  def findUserById(id:Long):Future[Option[User]]=db.run(Users.filter(_.recid===id).result.headOption)

  def findUserAddress(id:Long):Future[Option[Address]] = db.run(Addresses.filter(_.addressId===id).result.headOption)

  def deleteUserById(id:Long):Future[Int]=db.run(Users.filter(_.recid===id).delete)

  def updateUser(id :Long,user:User):Future[Int] = db.run(Users.filter(_.recid===id).update(user))

  def isEmpty: Boolean = !Await.result(db.run(Users.exists.result), Duration.Inf)
}
