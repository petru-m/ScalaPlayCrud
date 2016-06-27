import dao.{AddressDAO, UserDAO}
import model.{Address, User}
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.Logger
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import org.specs2.mutable.Specification
import play.api.test.WithApplicationLoader


/**
 * Created by petru on 30.10.2015.
 */

@RunWith(classOf[JUnitRunner])
class DAOTest extends Specification{

  "DAOTest" should{
    "work as expected" in new WithApplicationLoader{

      val testAddress = Address(Some(2.toLong),"Street Name",2,"Locality","City","Country")
      val testUser = User(Some(2.toLong),"Petru","Miftode","petru.miftode+1@gmail.com")


      //add adddress
      Await.result(AddressDAO.addAddress(testAddress),1 seconds)
      val storedAddress = Await.result(AddressDAO.allAddresses(),1 seconds)
      storedAddress.contains(testAddress) must beTrue

      //add user
      Await.result(UserDAO.add(testUser),1 seconds)
      val storedUser = Await.result(UserDAO.all(),1 seconds)
      storedUser.contains(testUser) must beTrue


      //update address
      val newAddress= Address(Some(2.toLong),"streetName",3,"locality","city","country")
      val updateAddress= Await.result(AddressDAO.updateAddress(testAddress.addressId.get,newAddress),1 seconds)
      val all = Await.result(AddressDAO.allAddresses(),1 seconds)
      all.contains(newAddress) must beTrue

      //update user
      val upUser= User(Some(2.toLong),"petru","miftode","petru.miftode+2@gmail.com",Some(newAddress.addressId.get))
      val updateUser = Await.result(UserDAO.updateUser(testUser.recid.get,upUser),1 seconds)
      val newUpdate=Await.result(UserDAO.findUserById(testUser.recid.get),1 seconds)
      newUpdate must beEqualTo(upUser.toOption)

      //get user Address
      val userAddress = Await.result(UserDAO.findUserAddress(newUpdate.get.addressId.get),1 seconds)
      userAddress must beEqualTo(newAddress.toOption)

      //delete user
      val deleteUser=Await.result(UserDAO.deleteUserById(testUser.recid.get),1 seconds)
      val deletedU=Await.result(UserDAO.findUserById(testUser.recid.get),1 seconds)
      deletedU must beNone

      //delete address
      val deleteAddress= Await.result(AddressDAO.deleteAddress(testAddress.addressId.get),1 seconds)
      val deletedA= Await.result(AddressDAO.allAddresses(),1 seconds)
      deletedA.contains(testAddress) must beFalse


      }
    }
}
