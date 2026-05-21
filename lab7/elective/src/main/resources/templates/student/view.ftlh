<#include "../_header.ftlh">

<div class="mb-4 d-flex align-items-start justify-content-between">
    <div>
        <a href="/students" class="text-muted text-decoration-none small">
            <i class="bi bi-arrow-left me-1"></i>Студенти
        </a>
        <h1 class="page-title mt-1">${student.fullName}</h1>
    </div>
    <div class="d-flex gap-2">
        <a href="/students/${student.id}/edit" class="btn btn-outline-primary btn-sm">
            <i class="bi bi-pencil me-1"></i>Редагувати
        </a>
        <a href="/registrations/new?studentId=${student.id}" class="btn btn-primary btn-sm">
            <i class="bi bi-clipboard-plus me-1"></i>Записати на курс
        </a>
    </div>
</div>

<div class="row g-3">
    <!-- Профіль -->
    <div class="col-md-3">
        <div class="card text-center p-3">
            <div class="mx-auto mb-3 d-flex align-items-center justify-content-center rounded-circle bg-primary bg-opacity-10 text-primary fw-bold"
                 style="width:72px;height:72px;font-size:1.5rem;">
                ${student.firstName?substring(0,1)}${student.lastName?substring(0,1)}
            </div>
            <div class="fw-bold fs-5">${student.fullName}</div>
            <div class="text-muted small mt-1">${student.email}</div>
            <div class="mt-2">
                <span class="badge bg-primary bg-opacity-10 text-primary">${student.courseYear} курс</span>
            </div>
        </div>
    </div>

    <!-- Курси та оцінки -->
    <div class="col-md-9">
        <div class="card table-card mb-3">
            <div class="card-header d-flex align-items-center justify-content-between">
                <span><i class="bi bi-clipboard-check me-2 text-warning"></i>Записи на курси</span>
                <span class="badge bg-secondary">${registrations?size}</span>
            </div>
            <table class="table mb-0">
                <thead>
                    <tr><th>Курс</th><th>Викладач</th><th>Дата</th><th></th></tr>
                </thead>
                <tbody>
                    <#if registrations?has_content>
                        <#list registrations as r>
                        <tr>
                            <td><a href="/courses/${r.course.id}">${r.course.name}</a></td>
                            <td class="text-muted">${(r.course.teacher.fullName)!"—"}</td>
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
                        <tr><td colspan="4">
                            <div class="empty-state text-center py-3">Немає записів</div>
                        </td></tr>
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
                <thead>
                    <tr><th>Курс</th><th>Оцінка</th><th>Дата завершення</th></tr>
                </thead>
                <tbody>
                    <#if grades?has_content>
                        <#list grades as a>
                        <tr>
                            <td>${a.registration.course.name}</td>
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
                        <tr><td colspan="3">
                            <div class="empty-state text-center py-3">Немає оцінок</div>
                        </td></tr>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#include "../_footer.ftlh">
