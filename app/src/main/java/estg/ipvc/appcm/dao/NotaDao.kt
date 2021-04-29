package estg.ipvc.appcm.dao

import androidx.room.*
import estg.ipvc.appcm.entities.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {

    @Query("SELECT * FROM nota_table ORDER BY titulo ASC")
    fun getAlphabetizedWords(): Flow<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNota(nota: Nota)

}