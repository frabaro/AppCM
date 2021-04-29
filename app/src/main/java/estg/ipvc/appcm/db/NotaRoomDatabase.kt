package estg.ipvc.appcm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import estg.ipvc.appcm.entities.Nota
import estg.ipvc.appcm.dao.NotaDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Nota class
@Database(entities = arrayOf(Nota::class), version = 4, exportSchema = false)
abstract class NotaRoomDatabase : RoomDatabase() {

    abstract fun notaDao(): NotaDao

    private class NotaDatabaseCallback(
        private val scope: CoroutineScope
    ):RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notaDao = database.notaDao()

                    // Delete all content here.
                    notaDao.deleteAll()

                    // Add sample words.
                    var nota1 = Nota(1, "Nota 1", "asdfeargertg")
                    notaDao.insert(nota1)
                    var nota2 = Nota(2, "Nota 2", "farefboiwbnhu")
                    notaDao.insert(nota2)
                }
            }
        }

        /* override fun onOpen(db: SupportSQLiteDatabase) {
             super.onOpen(db)
             INSTANCE?.let { database ->
                 scope.launch{
                     var notaDao = database.notaDao()

                     notaDao.deleteAll()

                     var nota = Nota(1, "Nota 1", "asdfeargertg")
                     notaDao.insert(nota)
                     var nota = Nota(2, "Nota 2", "farefboiwbnhu")
                     notaDao.insert(nota)
                 }

             }
         }*/
    }

    companion object {
        @Volatile
        private var INSTANCE: NotaRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NotaRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaRoomDatabase::class.java,
                    "nota_database"
                )
                    //.fallbackToDestructiveMigration()
                    .addCallback(NotaDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}



