package com.example.examen01

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot


class DispositivoFirestore {

    val db = Firebase.firestore

    fun crearDispositivo(nuevoDisp: Dispositivo): Task<Void> {
        val documentReference = db.collection("dispositivos").document(nuevoDisp.idDispositivo.toString())
        return documentReference.set(nuevoDisp)
    }

    fun obtenerDispositivos(callback: (ArrayList<Dispositivo>) -> Unit) {
        val dispList = ArrayList<Dispositivo>()
        db.collection("dispositivos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val disp = document.toObject(Dispositivo::class.java)
                    disp.idDispositivo = document.id.toInt()
                    dispList.add(disp)
                }
                callback(dispList)
            }
            .addOnFailureListener { exception -> }
    }

    fun consultarDispPorID(codigoUnico: String): Task<DocumentSnapshot> {
        val documentReference = db.collection("dispositivos").document(codigoUnico)
        return documentReference.get()
    }

    fun actualizarDispPorID(datosActualizados: Dispositivo): Task<Void> {
        val documentReference = db.collection("dispositivos").document(datosActualizados.idDispositivo.toString())
        val data = hashMapOf(
            "nombreDispositivo" to datosActualizados.nombreDispositivo,
            "fechaCreacion" to datosActualizados.fechaCreacion,
            "stock" to datosActualizados.stock,
            "precio" to datosActualizados.precio
        )

        return documentReference.set(data)
    }

    fun eliminarDispPorID(codigoUnico: String): Task<Void> {
        val documentReference = db.collection("dispositivos").document(codigoUnico)
        return documentReference.delete()
    }

    fun obtenerPiezasPorDispositivo(identificadorDisp: String, callback: (ArrayList<Pieza>) -> Unit) {
        val piezas = ArrayList<Pieza>()
        val collectionReference = db.collection("dispositivos").document(identificadorDisp).collection("piezas")

        collectionReference.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val pieza = document.toObject(Pieza::class.java)
                    pieza?.idPieza = document.id.toInt()
                    pieza?.let {
                        piezas.add(it)
                    }
                }
                callback(piezas)
            }
            .addOnFailureListener { exception ->
            }
    }

    fun crearPieza(nuevaPieza: Pieza): Task<Void> {
        val dispDocumentReference = db.collection("dispositivos").document(nuevaPieza.idPieza.toString())
        val piezasCollectionReference = dispDocumentReference.collection("piezas")

        val documentReference = piezasCollectionReference.document(nuevaPieza.idPieza.toString())

        return documentReference.set(nuevaPieza)
    }

    fun consultarPiezaPorIdentificadorYDispo(identificador: String, idDispo: String): Task<DocumentSnapshot> {
        val documentReference = db.collection("dispositivos").document(idDispo)
            .collection("piezas").document(identificador)
        return documentReference.get()
    }

    fun actualizarPiezaPorIdentificadorEIDisp(datosActualizados: Pieza): Task<Void> {
          val documentReference = db.collection("dispositivos").document(datosActualizados.disp.toString())
            .collection("piezas").document(datosActualizados.idPieza.toString())

        return documentReference.set(datosActualizados)
    }

    fun eliminarPiezaPorIdEIdDisp(idPieza: String, idDisp: String): Task<Void> {
        val documentReference = db.collection("dispositivos").document(idDisp)
            .collection("piezas").document(idPieza)
        return documentReference.delete()
    }







}