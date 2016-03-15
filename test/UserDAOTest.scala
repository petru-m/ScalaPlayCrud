import dao.UserDAO
import model.User
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
class UserDAOTest extends Specification{

  "UserDAOTest" should{
      "work as expected" in new WithApplicationLoader {

        val testUser = User(Some(2.toLong),"Petru","Miftode","petru.miftode+1@gmail.com")

        Await.result(UserDAO.add(testUser),1 seconds)
        val stored = Await.result(UserDAO.all(),1 seconds)
        stored.contains(testUser) must beTrue

        val findUser = Await.result(UserDAO.findUserById(testUser.recid.get),1 seconds)
        findUser must beOneOf(testUser.toOption)

        val upUser= User(Some(2.toLong),"petru","miftode","petru.miftode+2@gmail.com")
        val updateUser = Await.result(UserDAO.updateUser(testUser.recid.get,upUser),1 seconds)
        val newUpdate=Await.result(UserDAO.findUserById(testUser.recid.get),1 seconds)
        newUpdate must beEqualTo(upUser.toOption)

        val deleteUser=Await.result(UserDAO.deleteUserById(testUser.recid.get),1 seconds)
        val deleted=Await.result(UserDAO.findUserById(testUser.recid.get),1 seconds)
        deleted must beNone
      }
    }
}
