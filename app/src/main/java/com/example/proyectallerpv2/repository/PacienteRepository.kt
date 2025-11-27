package com.example.proyectallerpv2.repository

import com.example.proyectallerpv2.model.Paciente

// Repositorio temporal que guarda pacientes en memoria
class PacienteRepository {
    // Lista mutable privada que almacena los pacientes
    private val pacientes = mutableListOf<Paciente>()

    /**
     * Agrega un nuevo paciente al repositorio
     * @param paciente El paciente a agregar
     */
    fun agregarPaciente(paciente: Paciente) {
        pacientes.add(paciente)
    }

    /**
     * Obtiene todos los pacientes almacenados
     * @return Lista inmutable de pacientes
     */
    fun obtenerTodosPacientes(): List<Paciente> {
        return pacientes.toList()
    }

    /**
     * Busca un paciente por RUT
     * @param rut El RUT del paciente a buscar
     * @return El paciente si existe, null si no
     */
    fun buscarPorRut(rut: String): Paciente? {
        return pacientes.find { it.rut == rut }
    }

    /**
     * Elimina un paciente del repositorio
     * @param paciente El paciente a eliminar
     * @return true si se eliminó, false si no existía
     */
    fun eliminarPaciente(paciente: Paciente): Boolean {
        return pacientes.remove(paciente)
    }

    /**
     * Obtiene la cantidad total de pacientes
     * @return Número de pacientes almacenados
     */
    fun obtenerCantidad(): Int {
        return pacientes.size
    }

    /**
     * Limpia todos los pacientes del repositorio
     */
    fun limpiarTodos() {
        pacientes.clear()
    }

    companion object {
        // Instancia singleton del repositorio
        private var instance: PacienteRepository? = null

        /**
         * Obtiene la instancia única del repositorio
         * @return Instancia singleton de PacienteRepository
         */
        fun getInstance(): PacienteRepository {
            if (instance == null) {
                instance = PacienteRepository()
            }
            return instance!!
        }
    }
}
