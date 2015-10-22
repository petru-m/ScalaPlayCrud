import dao.UserDAO
import model.User
import play.api.{Application, GlobalSettings}

/**
 * Created by petru on 22.10.2015.
 * */

object Global  extends GlobalSettings{

  override def onStart(app:Application) : Unit={
    val dao = new UserDAO

    if(dao.isEmpty){
      dao.add(User(
      recid = None,
      first_name = "Petru",
      last_name = "Miftode",
      email = "petru.miftode@assist.ro"
      ))
    }
  }

}

