package com.vmmarinc.sweetanikca2.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.data.databases.dao.CommentDao
import com.vmmarinc.sweetanikca2.data.mock.CommentMock
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class CommentRepository(private val dataSource: CommentDao, private val firebaseDataSource: FirebaseFirestore) {
    private val db: CollectionReference = firebaseDataSource.collection(Constants.COMMENT_COLECTION)

    suspend fun loadComments(): List<Comment>{
       /* return withContext(Dispatchers.IO){
            dataSource.getAllComments()
        }*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Comment::class.java)
    }
    suspend fun insertComments(comments: List<Comment>){
        withContext(Dispatchers.IO){
            val temp = dataSource.getAllComments()
            if (temp.isEmpty())   {
                dataSource.insertComments(comments)
            }
        }

    }
}