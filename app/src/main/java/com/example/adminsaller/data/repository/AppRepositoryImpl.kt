package com.example.adminsaller.data.repository

import android.util.Log
import com.example.adminsaller.data.model.SellerData
import com.example.adminsaller.data.remote.request.SellerRequest
import com.example.adminsaller.domain.repository.AppRepository
import com.example.adminsaller.utils.getAllLive
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : AppRepository {

    override fun addSeller(request: SellerRequest): Flow<Result<SellerData>> = callbackFlow {

        fireStore.collection("Sellers")
            .add(request)
            .addOnSuccessListener {
                Log.d("TTT", "addSeller: ${it.id}")
                trySend(Result.success(SellerData(it.id, request.sellerName, request.password)))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun deleteSeller(sellerData: SellerData): Flow<Result<String>> = callbackFlow {
        Log.d("TTT", "deleteSellerId:${sellerData.id} ")
        fireStore.collection("Sellers")
            .document(sellerData.id)
            .delete()
            .addOnSuccessListener {
                trySend(Result.success("Seller muvvaffiqiyatli o'chirildi"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getSellers(): Flow<Result<List<SellerData>>> = callbackFlow {
        fireStore
            .collection("Sellers")
            .getAllLive {
                return@getAllLive SellerData(
                    it.id,
                    it.data?.getOrDefault("sellerName", "") as String,
                    it.data?.getOrDefault("password", "") as String
                )
            }
            .onEach { trySend(it.onSuccess { it }) }
            .collect()
        awaitClose()

    }

    override fun editSeller(id: String, sellerName: String, password: String): Flow<Result<String>> =
        callbackFlow {
            fireStore.collection("Sellers")
                .document(id)
                .set(SellerData(id, sellerName, password))
                .addOnSuccessListener {
                    trySend(Result.success("success"))
                }
                .addOnFailureListener{
                    trySend(Result.failure(it))
                }
            awaitClose()
        }
}