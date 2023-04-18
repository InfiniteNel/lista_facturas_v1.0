package com.jroslar.listafacturasv01.data

import android.content.Context
import com.jroslar.listafacturasv01.data.database.FacturasDatabase
import com.jroslar.listafacturasv01.data.model.FacturaModel
import com.jroslar.listafacturasv01.data.network.FacturasService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FacturasRepository {
    val facturasService = FacturasService()
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    suspend fun getAllFacturasFromApi(): List<FacturaModel> {
        return withContext(dispatcherIO) {
            val result = facturasService.getFaturas()

            result.facturas
        }
    }

    suspend fun clearFacturas(context: Context) {
        return withContext(dispatcherIO) {
            FacturasDatabase.getInstance(context).facturaDao().clearDataBase()
        }
    }

    suspend fun insertFacturas(context: Context, facturas: List<FacturaModel>) {
        return withContext(dispatcherIO) {
            FacturasDatabase.getInstance(context).facturaDao().insertFacturas(facturas)
        }
    }

    suspend fun getAllFacturasLocal(context: Context): List<FacturaModel> {
        return  withContext(dispatcherIO) {
            FacturasDatabase.getInstance(context).facturaDao().getFacturas()
        }
    }
}