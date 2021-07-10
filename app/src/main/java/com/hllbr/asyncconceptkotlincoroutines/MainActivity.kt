package com.hllbr.asyncconceptkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    var userName = ""
    var userAge = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        Async kavramı coroutinesler ile birlikte nasıl kullanılır ?

        launch ibaresi yerine async kavramını kullanarak farklı işlemler yapabiliriz.
         */
        main()//stabil method
        main1()
    }
     fun main(){
        /*
        Senaryoda internetten veri indiriyoruz biri kullanıcının yaşı diğeri adı olsun bunları ne zaman indireceğimiz belli olmaya bilir.
        Aynı anda mı insinler insinler sonra mı işlem yapalım nasıl karar veriyoruz ?
        Bu sorunları Async yapılaradn faydalanarak çözebiliyoruz.
        Async yapı bir cevap bekler bizden
         */
        runBlocking {
            launch {
                val downloadedName = downloadName()
                println("${downloadedName}")

            }
            launch {
                val downloadedAge = userAge()
                println("${downloadedAge}")

            }
        }

    }
    fun main1(){
        runBlocking {
            val downloadedName = async {
            downloadName()
        }
            val downloadedAge = async {
                userAge()
            }
            userName = downloadedName.await()
            userAge = downloadedAge.await()//bu yapıların bitmesi beklenecek atama işleminin gerçekleştirilmesi için şimdi println() çalıştığında 0 ve boş değerler ile karşılaşılmayacak

            println("user_Name ,\n${userName}," +
                    "\nuser_Age ,\n${userAge}")
        }
    }
    suspend fun downloadName() : String{//coroutines işlemler yapacaksak yapıyı suspend gerekli
        delay(2000)
        val userName = "HLLBR"
        val userName1 = "HİKOCAK"
        val userName2 = "HALİBR9"
        val userName3 = userName +userName2 +userName1
        println("username Download")
        return userName3
    }
    suspend fun userAge() :Int{
        delay(4000)
        val userAge1  = 60
        //val userAge2  = 60
        //val userAge3  = 60
        //val userAge4 :String = "user0${userAge1},user1${userAge2},user2${userAge3}"
        //println(userAge4)
        return userAge1
    }

}