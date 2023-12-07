package com.example.adminsaller.data.repository

import android.util.Log
import com.example.adminsaller.data.model.ProductsData
import com.example.adminsaller.data.model.SellerData
import com.example.adminsaller.data.model.toCommonData
import com.example.adminsaller.data.remote.request.ProductRequest
import com.example.adminsaller.data.remote.request.SellerRequest
import com.example.adminsaller.domain.repository.AppRepository
import com.example.adminsaller.utils.getAllLive
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : AppRepository {

    private val realtTimeFireBase = Firebase.database.getReference("Products")

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

    override fun editSeller(
        id: String,
        sellerName: String,
        password: String
    ): Flow<Result<String>> =
        callbackFlow {
            fireStore.collection("Sellers")
                .document(id)
                .set(SellerData(id, sellerName, password))
                .addOnSuccessListener {
                    trySend(Result.success("success"))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }

    override fun addProduct(productRequest: ProductRequest): Flow<Result<ProductsData>> =
        callbackFlow {
            val uuid = realtTimeFireBase.push().key ?: UUID.randomUUID().toString()
            Log.d("TAG", "addProduct: ")
            realtTimeFireBase.child(uuid).setValue(productRequest).addOnSuccessListener {
                trySend(
                    Result.success(
                        ProductsData(
                            productRequest.productID,
                            productRequest.productName,
                            productRequest.productCount,
                            productRequest.productInitialPrice,
                            productRequest.productSellingPrice,
                            productRequest.productIsValid,
                            productRequest.productComment
                        )
                    )
                )
            }
            awaitClose()
        }

    override fun deleteProduct(productsData: ProductsData): Flow<Result<String>> = callbackFlow {
        realtTimeFireBase.child(productsData.productID).removeValue()
        awaitClose()
    }

    override fun getAllProducts(): Flow<List<ProductsData>> = callbackFlow {
        realtTimeFireBase
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(snapshot.children.map { it.toCommonData() })
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        awaitClose()
    }

    override fun editProducts(
        productID: String,
        productName: String,
        productCount: Int,
        productInitialPrice: Int,
        productSellingPrice: Int,
        productIsValid: Boolean,
        productComment: String
    ): Flow<Result<String>> = callbackFlow {

    }
}