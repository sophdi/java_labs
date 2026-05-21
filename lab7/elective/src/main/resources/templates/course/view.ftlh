<#include "../_header.ftlh">

<div class="mb-4 d-flex align-items-start justify-content-between">
    <div>
        <a href="/courses" class="text-muted text-decoration-none small">
            <i class="bi bi-arrow-left me-1"></i>Курси
        </a>
        <h1 class="page-title mt-1">${course.name}</h1>
    </div>
    <div class="d-flex gap-2">
        <a href="/courses/${course.id}/edit" class="btn btn-outline-primary btn-sm">
            <i class="bi bi-pencil me-1"></i>Редагувати
        </a>
        <a href="/registrations/new?courseId=${course.id}" class="btn btn-primary btn-sm">
            <i class="bi bi-person-plus me-1"></i>Записати студента
        </a>
    </div>
</div>

<div class="row g-3">
    <div class="col-md-3">
        <div class="card p-3 mb-3">
            <div class="d-flex align-items-center justify-content-center rounded-3 mb-3"
                 style="height:60px;background:#e0f2fe;color:#0284c7;">
                <i class="bi bi-book-fill fs-2"></i>
            </div>
            <div class="small text-muted mb-1">Тривалість</div>
            <div class="fw-bold">${course.durationWeeks} тижнів</div>
            <div class="small text-muted mt-2 mb-1">Викладач</div>
            <div class="fw-bold">${(course.teacher.fullName)!"—"}</div>
            <#if course.description?? && course.description?has_content>
                <div class="small text-muted mt-2 mb-1">Опис</div>
                <div class="small">${course.description}</div>
            </#if>
        </div>
        <a href="/archive/grade" class="btn btn-outline-success w-100 btn-sm">
            <i class="bi bi-award me-1"></i>Виставити оцінку
        </a>
    </div>

    <div class="col-md-9">
        <div class="card table-card mb-3">
            <div class="card-header d-flex align-items-center justify-content-between">
                <span><i class="bi bi-people me-2 text-primary"></i>Записані студенти</span>
                <span class="badge bg-secondary">${registrations?size}</span>
            </div>
            <table class="table mb-0">
                <thead><tr><th>Студент</th><th>Курс навчання</th><th>Дата запису</th><th></th></tr></thead>
                <tbody>
                    <#if registrations?has_content>
                        <#list registrations as r>
                        <tr>
                            <td><a href="/students/${r.student.id}">${r.student.fullName}</a></td>
                            <td><span class="badge bg-primary bg-opacity-10 text-primary">${r.student.courseYear} курс</span></td>
                            <td class="text-muted small">${r.registrationDate}</td>
                            <td class="text-end">
                                <form method="post" action="/registrations/${r.id}/delete" class="d-inline"
                                      onsubmit="return confirm('Скасувати запис?')">
                                    <button class="btn btn-sm btn-outline-danger btn-icon">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                        <tr><td colspan="4"><div class="empty-state text-center py-3">Немає записів</div></td></tr>
                    </#if>
                </tbody>
            </table>
        </div>

        <div class="card table-card">
            <div class="card-header d-flex align-items-center justify-content-between">
                <span><i class="bi bi-award me-2 text-success"></i>Оцінки</span>
                <span class="badge bg-secondary">${grades?size}</span>
            </div>
            <table class="table mb-0">
                <thead><tr><th>Студент</th><th>Оцінка</th><th>Дата</th></tr></thead>
                <tbody>
                    <#if grades?has_content>
                        <#list grades as a>
                        <tr>
                            <td>${a.registration.student.fullName}</td>
                            <td>
                                <#if a.grade gte 90>
                                    <span class="badge bg-success">${a.grade} — Відмінно</span>
                                <#elseif a.grade gte 75>
                                    <span class="badge bg-primary">${a.grade} — Добре</span>
                                <#elseif a.grade gte 60>
                                    <span class="badge bg-warning text-dark">${a.grade} — Задовільно</span>
                                <#else>
                                    <span class="badge bg-danger">${a.grade} — Незадовільно</span>
                                </#if>
                            </td>
                            <td class="text-muted small">${a.completionDate}</td>
                        </tr>
                        </#list>
                    <#else>
                        <tr><td colspan="3"><div class="empty-state text-center py-3">Немає оцінок</div></td></tr>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#include "../_footer.ftlh">
