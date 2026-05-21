<#include "../_header.ftlh">

<div class="mb-4">
    <a href="/teachers" class="text-muted text-decoration-none small">
        <i class="bi bi-arrow-left me-1"></i>Викладачі
    </a>
    <h1 class="page-title mt-1">${(teacher.id??)?then("Редагувати викладача", "Новий викладач")}</h1>
</div>

<div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
        <div class="card">
            <div class="card-body p-4">
                <#assign action = (teacher.id??)?then("/teachers/${teacher.id}/edit", "/teachers")>
                <form method="post" action="${action}">
                    <div class="form-floating mb-3">
                        <input type="text" id="firstName" name="firstName" class="form-control"
                               placeholder="Ім'я" value="${(teacher.firstName)!}" required>
                        <label for="firstName">Ім'я *</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" id="lastName" name="lastName" class="form-control"
                               placeholder="Прізвище" value="${(teacher.lastName)!}" required>
                        <label for="lastName">Прізвище *</label>
                    </div>
                    <div class="form-floating mb-4">
                        <input type="email" id="email" name="email" class="form-control"
                               placeholder="Email" value="${(teacher.email)!}" required>
                        <label for="email">Email *</label>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary flex-grow-1">
                            <i class="bi bi-check-lg me-1"></i>Зберегти
                        </button>
                        <a href="/teachers" class="btn btn-outline-secondary">Скасувати</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#include "../_footer.ftlh">
