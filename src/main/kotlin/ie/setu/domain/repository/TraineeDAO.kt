package ie.setu.domain.repository
import ie.setu.domain.Trainee
import ie.setu.domain.db.Trainees
import ie.setu.domain.Login
import ie.setu.utils.mapToLogin
import ie.setu.utils.mapToTrainee
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


//--------------------------------------------------------------
// TraineeDAO specifics
//-------------------------------------------------------------
class TraineeDAO {
    fun getAllTrainees(): ArrayList<Trainee> {
        val traineesList: ArrayList<Trainee> = arrayListOf()
        transaction {
            Trainees.selectAll().map {
                traineesList.add(mapToTrainee(it))}
        }
        return traineesList
    }

    fun save(trainee: Trainee): Int{
        return transaction {
            Trainees.insert {
                it[name]=trainee.name
                it[email]=trainee.email
                it[phone]=trainee.phone
                it[address]=trainee.address
                it[city]= trainee.city
                it[eircode]=trainee.eircode
                it[country]=trainee.country
                it[date_of_birth]=trainee.date_of_birth
                it[gender]=trainee.gender
                it[emergency_contact_name]=trainee.emergency_contact_name
                it[emergency_contact_phone]=trainee.emergency_contact_phone
                it[emergency_contact_relation]=trainee.emergency_contact_relation
                it[membership_type]= trainee.membership_type
                it[membership_start_date]=trainee.membership_start_date
                it[payment_method]= trainee.payment_method
                it[amount]=trainee.amount
                it[height]=trainee.height
                it[weight]=trainee.weight
                it[login]=trainee.login
            }
        }get Trainees.id
    }

    fun findTraineeByLoginId(id: Int): Trainee?{
        return transaction {
            Trainees.select() {
                Trainees.login eq id}
                .map{ mapToTrainee(it) }
                .firstOrNull()
        }
    }

    fun delete(id: Int): Int{
        return transaction{
            Trainees.deleteWhere{
                Trainees.id eq id
            }
        }
    }

    fun update(id: Int, trainee: Trainee): Int{
        return transaction {
            Trainees.update ({
                Trainees.id eq id}) {
                it[name] = trainee.name
                it[email] = trainee.email
                it[phone]=trainee.phone
                it[address]=trainee.address
                it[city]= trainee.city
                it[eircode]=trainee.eircode
                it[country]=trainee.country
                it[date_of_birth]=trainee.date_of_birth
                it[gender]=trainee.gender
                it[emergency_contact_name]=trainee.emergency_contact_name
                it[emergency_contact_phone]=trainee.emergency_contact_phone
                it[emergency_contact_relation]=trainee.emergency_contact_relation
                it[membership_type]= trainee.membership_type
                it[membership_start_date]=trainee.membership_start_date
                it[payment_method]= trainee.payment_method
                it[amount]=trainee.amount
                it[height]=trainee.height
                it[weight]=trainee.weight
                it[login]=trainee.login
            }
        }
    }
}

//--------------------------------------------------------------
// LoginDAO specifics
//-------------------------------------------------------------

class LoginDAO {
    fun getAlllogin(): ArrayList<Login> {
        val loginList: ArrayList<Login> = arrayListOf()
        transaction {
            Trainees.selectAll().map {
                loginList.add(mapToLogin(it))}
        }
        return loginList
    }
}