package com.example.adminsaller.data.repository

import com.example.adminsaller.data.model.SellerData
import com.example.adminsaller.data.remote.request.SellerRequest
import com.example.adminsaller.domain.repository.AppRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : AppRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun addSeller(request: SellerRequest): Flow<Result<String>> = callbackFlow {

        fireStore.collection("Sellers")
            .add(request)
        awaitClose()
    }

    override fun deleteSeller(sellerData: SellerData): Flow<Result<String>> = callbackFlow {
        fireStore.collection("Sellers")
            .document(sellerData.id)
            .delete()
            .addOnSuccessListener {
                trySend(Result.success("User muvvaffiqiyatli o'chirildi"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getSellers(): Flow<Result<List<SellerData>>> = flow {
        getUser()
    }


    private fun getUser(): Flow<Unit> = callbackFlow {
        val resultList = arrayListOf<SellerData>()
        fireStore.collection("Sellers")
            .get()
            .addOnSuccessListener { data ->
                data.documents.forEach {
                    resultList.add(
                        SellerData(
                            id = it.id,
                            name = it.data?.getOrDefault("sellerName", "").toString(),
                            password = it.data?.getOrDefault("password", "").toString()
                        )
                    )
                }
                trySend(Unit)
            }
            .addOnFailureListener {
                trySend(Unit)
            }

        awaitClose()
    }

}