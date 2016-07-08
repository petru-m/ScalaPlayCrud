package dao

import model.Address
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import _root_.slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

/**
  * Created by Petru on 3/15/2016.
  */
object AddressDAO extends HasDatabaseConfig[JdbcProfile]{
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  private val Addresses = TableQuery[AddressTable]

  class AddressTable(tag:Tag)extends Table[Address](tag,"address"){
    def addressId = column[Long]("addressId", O.PrimaryKey, O.AutoInc)
    def streetName = column[String]("streetName")
    def number = column[Long]("number")
    def locality = column[String]("locality")
    def city = column[String]("city")
    def country =column[String]("country")

    def * = (addressId.?,streetName,number,locality,city,country)<>(Address.tupled, Address.unapply _)
  }

  def allAddresses(): Future[List[Address]] = db.run(Addresses.result).map(_.toList)

  def addAddress(address: Address):Future[Long] = db.run(Addresses returning Addresses.map(_.addressId) +=address).map(_.toLong)

  def deleteAddress(addressId:Long):Future[Int] = db.run(Addresses.filter(_.addressId===addressId).delete)

  def updateAddress(addressId:Long,addreess:Address):Future[Int] = db.run(Addresses.filter(_.addressId===addressId).update(addreess))

  def isEmpty: Boolean = !Await.result(db.run(Addresses.exists.result), Duration.Inf)


}
