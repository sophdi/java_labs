<#include "../_header.ftlh">

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="page-title">Студенти</h1>
        <p class="text-muted mb-0 small">Список зареєстрованих студентів</p>
    </div>
    <a href="/students/new" class="btn btn-primary">
        <i class="bi bi-person-plus-fill me-1"></i>Додати
    </a>
</div>

<div class="card table-card">
    <table class="table mb-0">
        <thead>
            <tr>
                <th>#</th>
                <th>Студент</th>
                <th>Email</th>
                <th>Курс</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <#if students?has_content>
                <#list students as s>
                <tr>
                    <td class="text-muted small">${s.id}</td>
                    <td>
                        <a href="/students/${s.id}" class="d-flex align-items-center gap-2">
                            <span class="d-inline-flex align-items-center justify-content-center rounded-circle bg-primary bg-opacity-10 text-primary fw-bold"
                                  style="width:34px;height:34px;font-size:0.8rem;flex-shrink:0;">
                                ${s.firstName?substring(0,1)}${s.lastName?substring(0,1)}
                            </span>
                            ${s.fullName}
                        </a>
                    </td>
                    <td class="text-muted">${s.email}</td>
                    <td><span class="badge bg-primary bg-opacity-10 text-primary">${s.courseYear} курс</span></td>
                    <td class="text-end">
                        <div class="d-flex gap-1 justify-content-end">
                            <a href="/students/${s.id}" class="btn btn-sm btn-outline-secondary btn-icon" title="Переглянути">
                                <i class="bi bi-eye"></i>
                            </a>
                            <a href="/students/${s.id}/edit" class="btn btn-sm btn-outline-primary btn-icon" title="Редагувати">
                                <i class="bi bi-pencil"></i>
                            </a>
                            <form method="post" action="/students/${s.id}/delete" class="d-inline"
                                  onsubmit="return confirm('Видалити студента ${s.fullName}?')">
                                <button class="btn btn-sm btn-outline-danger btn-icon" title="Видалити">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
                </#list>
            <#else>
                <tr>
                    <td colspan="5">
                        <div class="empty-state text-center">
                            <i class="bi bi-people fs-1 d-block mb-2"></i>
                            Студентів поки немає
                        </div>
                    </td>
                </tr>
            </#if>
        </tbody>
    </table>
</div>

<#include "../_footer.ftlh">
