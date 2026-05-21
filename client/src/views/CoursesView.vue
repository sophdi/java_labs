<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API = 'http://localhost:8080/api/courses'

const courses = ref([])
const showForm = ref(false)
const editingId = ref(null)

const form = ref({
  name: '',
  durationWeeks: 1,
  description: ''
})

async function loadCourses() {
  const res = await axios.get(API)
  courses.value = res.data
}

function openCreate() {
  editingId.value = null
  form.value = { name: '', durationWeeks: 1, description: '' }
  showForm.value = true
}

function openEdit(course) {
  editingId.value = course.id
  form.value = { name: course.name, durationWeeks: course.durationWeeks, description: course.description || '' }
  showForm.value = true
}

async function save() {
  if (editingId.value) {
    await axios.put(`${API}/${editingId.value}`, form.value)
  } else {
    await axios.post(API, form.value)
  }
  showForm.value = false
  loadCourses()
}

async function remove(id) {
  if (!confirm('Видалити курс?')) return
  await axios.delete(`${API}/${id}`)
  loadCourses()
}

onMounted(loadCourses)
</script>

<template>
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2 class="fw-bold mb-0">Курси</h2>
    <button class="btn btn-primary" @click="openCreate">+ Додати</button>
  </div>

  <!-- Форма -->
  <div v-if="showForm" class="card mb-4">
    <div class="card-body">
      <h5 class="card-title">{{ editingId ? 'Редагувати курс' : 'Новий курс' }}</h5>
      <div class="row g-2">
        <div class="col-md-5">
          <input v-model="form.name" class="form-control" placeholder="Назва курсу" />
        </div>
        <div class="col-md-2">
          <input v-model.number="form.durationWeeks" class="form-control" type="number" min="1" placeholder="Тижнів" />
        </div>
        <div class="col-md-5">
          <input v-model="form.description" class="form-control" placeholder="Опис" />
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
          <th>Назва</th>
          <th>Викладач</th>
          <th>Тижнів</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="courses.length === 0">
          <td colspan="5" class="text-center text-muted py-4">Курсів немає</td>
        </tr>
        <tr v-for="c in courses" :key="c.id">
          <td class="text-muted">{{ c.id }}</td>
          <td>{{ c.name }}</td>
          <td>{{ c.teacher ? c.teacher.firstName + ' ' + c.teacher.lastName : '—' }}</td>
          <td>{{ c.durationWeeks }} тиж.</td>
          <td class="text-end">
            <button class="btn btn-sm btn-outline-primary me-1" @click="openEdit(c)">✏️</button>
            <button class="btn btn-sm btn-outline-danger" @click="remove(c.id)">🗑</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>