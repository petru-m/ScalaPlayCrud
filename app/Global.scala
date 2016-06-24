import dao.{AddressDAO, UserDAO}
import model.{Address, User}
import play.api.{Application, GlobalSettings}

/**
 * Created by petru on 22.10.2015.
 * */

object Global  extends GlobalSettings{

  override def onStart(app:Application) : Unit={

    if(AddressDAO.isEmpty){
      AddressDAO.addAddress(Address(
        addressId = None,
        streetName = "Street Name",
        number = 1,
        locality = "Locality",
        city = "City",
        country = "Country"
      ))
    }

    if(UserDAO.isEmpty){
      UserDAO.add(User(
      recid = None,
      first_name = "Petru",
      last_name = "Miftode",
      email = "petru.miftode@gmail.com",
      addressId = Some(1)
      ))
    }

  }

}

