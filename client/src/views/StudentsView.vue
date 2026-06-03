<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API = 'http://localhost:8080/api/students'

const students = ref([])
const showForm = ref(false)
const editingId = ref(null)

const form = ref({
  firstName: '',
  lastName: '',
  email: '',
  courseYear: 1
})

// Завантажити всіх студентів
async function loadStudents() {
  const res = await axios.get(API)
  students.value = res.data
}

// Відкрити форму для створення
function openCreate() {
  editingId.value = null
  form.value = { firstName: '', lastName: '', email: '', courseYear: 1 }
  showForm.value = true
}

// Відкрити форму для редагування
function openEdit(student) {
  editingId.value = student.id
  form.value = { ...student }
  showForm.value = true
}

// Зберегти (створити або оновити)
async function save() {
  if (editingId.value) {
    await axios.put(`${API}/${editingId.value}`, form.value)
  } else {
    await axios.post(API, form.value)
  }
  showForm.value = false
  loadStudents()
}

// Видалити студента
async function remove(id) {
  if (!confirm('Видалити студента?')) return
  await axios.delete(`${API}/${id}`)
  loadStudents()
}

onMounted(loadStudents)
</script>

<template>
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2 class="fw-bold mb-0">Студенти</h2>
    <button class="btn btn-primary" @click="openCreate">+ Додати</button>
  </div>

  <!-- Форма -->
  <div v-if="showForm" class="card mb-4">
    <div class="card-body">
      <h5 class="card-title">{{ editingId ? 'Редагувати' : 'Новий студент' }}</h5>
      <div class="row g-2">
        <div class="col-md-3">
          <input v-model="form.firstName" class="form-control" placeholder="Ім'я" />
        </div>
        <div class="col-md-3">
          <input v-model="form.lastName" class="form-control" placeholder="Прізвище" />
        </div>
        <div class="col-md-4">
          <input v-model="form.email" class="form-control" placeholder="Email" type="email" />
        </div>
        <div class="col-md-2">
          <select v-model="form.courseYear" class="form-select">
            <option v-for="y in 6" :key="y" :value="y">{{ y }} курс</option>
          </select>
        </div>
      </div>
      <div class="mt-2 d-flex gap-2">
        <button class="btn btn-success" @click="save">Зберегти</button>
        <button class="btn btn-outline-secondary" @click="showForm = false">Скасувати</button>
      </div>
    </div>
  </div>

  <!-- Таблиця -->
  <div class="card">
    <table class="table table-hover mb-0">
      <thead class="table-light">
        <tr>
          <th>#</th>
          <th>Ім'я</th>
          <th>Email</th>
          <th>Курс</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="students.length === 0">
          <td colspan="5" class="text-center text-muted py-4">Студентів немає</td>
        </tr>
        <tr v-for="s in students" :key="s.id">
          <td class="text-muted">{{ s.id }}</td>
          <td>{{ s.fullName }}</td>
          <td>{{ s.email }}</td>
          <td>{{ s.courseYear }} курс</td>
          <td class="text-end">
            <button class="btn btn-sm btn-outline-primary me-1" @click="openEdit(s)">✏️</button>
            <button class="btn btn-sm btn-outline-danger" @click="remove(s.id)">🗑</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
