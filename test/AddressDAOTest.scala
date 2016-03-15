import dao.AddressDAO
import model.Address
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.Logger
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import org.specs2.mutable.Specification
import play.api.test.WithApplicationLoader

/**
  * Created by Petru on 3/15/2016.
  */

//there is a problem with play slick integration that makes the tests fail->> https://github.com/playframework/playframework/issues/5466
/*
@RunWith(classOf[JUnitRunner])
class AddressDAOTest extends Specification{
    "AddressDAOTest" should{
      "work as expected" in new WithApplicationLoader{

        val testAddress = Address(Some(2.toLong),"Street Name",2,"Locality","City","Country")

        Await.result(AddressDAO.addAddress(testAddress),1 seconds)
        val stored = Await.result(AddressDAO.allAddresses(),1 seconds)
        stored.contains(testAddress) must beTrue

        val newAddress= Address(Some(2.toLong),"streetName",3,"locality","city","country")
        val updateAddress= Await.result(AddressDAO.updateAddress(testAddress.addressId.get,newAddress),1 seconds)
        val all = Await.result(AddressDAO.allAddresses(),1 seconds)
        all.contains(newAddress) must beTrue

        val deleteAddress= Await.result(AddressDAO.deleteAddress(testAddress.addressId.get),1 seconds)
        val deleted= Await.result(AddressDAO.allAddresses(),1 seconds)
        deleted.contains(testAddress) must beFalse
      }
    }
}
*/
