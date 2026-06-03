<script setup></script>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
    <span class="navbar-brand fw-bold">
      🎓 Факультатив
    </span>
    <div class="navbar-nav ms-auto">
      <router-link class="nav-link" to="/students">Студенти</router-link>
      <router-link class="nav-link" to="/courses">Курси</router-link>
    </div>
  </nav>

  <div class="container mt-4">
    <router-view />
  </div>
</template>

<style>
body { background: #f0f2f8; }
.router-link-active { color: #fff !important; font-weight: 600; }
</style>
