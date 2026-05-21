<#include "../_header.ftlh">

<div class="mb-4">
    <a href="/archive" class="text-muted text-decoration-none small">
        <i class="bi bi-arrow-left me-1"></i>Архів
    </a>
    <h1 class="page-title mt-1">Виставити оцінку</h1>
</div>

<div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
        <#if ungraded?has_content>
        <div class="card">
            <div class="card-body p-4">
                <form method="post" action="/archive/grade">
                    <div class="form-floating mb-3">
                        <select id="registrationId" name="registrationId" class="form-select" required>
                            <option value="">— Оберіть запис —</option>
                            <#list ungraded as r>
                                <option value="${r.id}">
                                    ${r.student.fullName} → ${r.course.name}
                                </option>
                            </#list>
                        </select>
                        <label for="registrationId">Студент → Курс *</label>
                    </div>
                    <div class="form-floating mb-2">
                        <input type="number" id="gradeValue" name="gradeValue" class="form-control"
                               placeholder="0" min="0" max="100" required>
                        <label for="gradeValue">Оцінка (0–100) *</label>
                    </div>
                    <div class="card mb-4 border-0" style="background:#f8fafc;">
                        <div class="card-body py-2 px-3">
                            <div class="row g-2 text-center small">
                                <div class="col-3"><span class="badge bg-success d-block">90–100</span><div class="text-muted mt-1">Відмінно</div></div>
                                <div class="col-3"><span class="badge bg-primary d-block">75–89</span><div class="text-muted mt-1">Добре</div></div>
                                <div class="col-3"><span class="badge bg-warning text-dark d-block">60–74</span><div class="text-muted mt-1">Задовільно</div></div>
                                <div class="col-3"><span class="badge bg-danger d-block">0–59</span><div class="text-muted mt-1">Незадовільно</div></div>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary flex-grow-1">
                            <i class="bi bi-check-lg me-1"></i>Виставити оцінку
                        </button>
                        <a href="/archive" class="btn btn-outline-secondary">Скасувати</a>
                    </div>
                </form>
            </div>
        </div>
        <#else>
        <div class="card">
            <div class="card-body p-4 text-center empty-state">
                <i class="bi bi-check-circle-fill text-success fs-1 d-block mb-3"></i>
                <h5>Всі курси вже оцінені</h5>
                <p class="text-muted small">Немає активних записів без оцінки.</p>
                <a href="/registrations/new" class="btn btn-primary btn-sm">
                    <i class="bi bi-clipboard-plus me-1"></i>Додати запис
                </a>
            </div>
        </div>
        </#if>
    </div>
</div>

<#include "../_footer.ftlh">
